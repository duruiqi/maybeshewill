package com.scxh.yhzm.action;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.User;
import com.scxh.yhzm.service.UserService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.ValidateCodeUtils;

public class UserAction extends BaseAction {
	private UserService<User> userService;
	private JavaMailSenderImpl javaMailSender;

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void setUserService(UserService<User> userService) {
		this.userService = userService;
	}

	//用户注册
	public String userRegister(HttpServletRequest request,
			HttpServletResponse response) {
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		Map<String, String> errors = new HashMap<String, String>();
		String backStr = "/frontPage/register";
		// 校验前台输入的参数值是否合法
		checkRegisterParams(request, errors,user);
		
		if (errors.size() > 0) {
			request.setAttribute("errorMsg", errors);
			request.setAttribute("confirmPassword",request.getParameter("confirmPassword"));
			request.setAttribute("form", user);
			return backStr;
		}

		User userTemp = userService.getEntryByCoulmn(user);
		
		try{
			if (userTemp == null) {
				// 向目标地址发送邮件
				postMail(user);
				// 保存用户的信息
				userService.addEntry(user);
				//转发到成功页面
				request.getSession().setAttribute("msg", "恭喜您！注册成功！请立即前往邮箱完成激活操作！");
				return "redirect:/frontPage/message.jsp";
			} else {
				errors.put("emailError", "抱歉！该邮箱已经注册过了或者用户名已经存在！");
				request.setAttribute("errorMsg", errors);
				request.setAttribute("confirmPassword",request.getParameter("confirmPassword"));
				request.setAttribute("form", user);
				return backStr;
			}
		}catch(Exception e){
			if(e instanceof MailException){
				request.setAttribute("msg",e.getMessage());
				request.setAttribute("form", user);
				return backStr;
			}
		}
		
		return backStr;
	}
	
	//用户的激活
	public String activeUser(HttpServletRequest request,HttpServletResponse response){
		String stateCode = request.getParameter("stateCode");
		String backAddr = "redirect:frontPage/message.jsp";
		try{
			if(stateCode != null && !stateCode.trim().isEmpty()){
				Integer back = userService.ativeUser(stateCode);
				String contextPath = this.getServletContext().getContextPath();
				if(back > 0){
					request.getSession().setAttribute("msg", 
							"恭喜您！完成激活操作，现在你可以登录了！<a href='" + contextPath + "/frontPage/login.jsp'>登录</a>");
				}else{
					throw new RuntimeException("激活失败");
				}
			}else{
				throw new RuntimeException("激活失败");
			}
		}catch(Exception e){
			request.getSession().setAttribute("msg",e.getMessage());
			return backAddr;
		}
		
		return backAddr;
	}
	
	//用户登录
	public String userLogin(HttpServletRequest request,HttpServletResponse response){
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		Map<String,String> errors = new HashMap<String, String>();
		checkLoginParams(request, errors, user);
		
		if(errors.size() > 0){
			request.setAttribute("form", user);
			request.setAttribute("errorMsg", errors);
			return "/frontPage/login";
		}
		user = userService.userLogin(user);
		
		if(user != null){
			request.getSession().setAttribute("user", user);
			
			return "redirect:frontPage/index.jsp";
		}
		request.setAttribute("form", CommonUtils.toBean(request.getParameterMap(), User.class));
		request.setAttribute("msg", "登录失败！密码错误或用户不存在！");
		return "/frontPage/login";
	}
	
	//退出登录
	public String userQuit(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("user");
		return "/frontPage/index";
	}
	
	//查询所有用户
	public String getUserList(HttpServletRequest request,HttpServletResponse response){
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		String curPage = request.getParameter("curPage");//获取当前页的角标
		
		map.put("curPage", null == curPage ? 1 : new Integer(curPage));
		
		map.put("pageSize", countPerPage);//一页固定要显示的纪录数

		try {
			PagingBean<User> pagingBean = userService.getAllEntryFY(map);
			request.setAttribute("pagingBean", pagingBean);
			return "backPage/user/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}
	
	//条件查询所有用户
	public String asynGetUserList(HttpServletRequest request,HttpServletResponse response){
		Map<String,String[]> tempMap = request.getParameterMap();
		Map<String,String> tMap = new HashMap<String, String>();
		
		for(Map.Entry<String, String[]>  entry : tempMap.entrySet()){
			
			String name = entry.getKey();
			String[] value = entry.getValue();
			
			if(value.length > 0){
				if(!value[0].trim().isEmpty()){
					tMap.put(name, value[0]);
				}else{
					tMap.put(name, null);
				}
			}
		}
		Map<String,Object> map = new HashMap<String, Object>(tMap);
		String curPage = request.getParameter("curPage");//获取当前页的角标
		map.put("curPage", null == curPage ? 1 : new Integer(curPage));
		map.put("pageSize", countPerPage);//一页固定要显示的纪录数
		
		try {
			PagingBean<User> pagingBean = userService.getAllEntryFY(map);
			request.setAttribute("pagingBean", pagingBean);
			return "backPage/part/aysnListUser";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}
	
	//后台修改用户备注
	public String modUserRemark(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		String remark = request.getParameter("remark");
		User user = new User();
		user.setUserId(userId);
		user.setRemark(remark);
		
		userService.modEntry(user);
		
		return getUserList(request, response);
	}
	//通过userId来获取一个用户对象
	public String getUser(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		User user = userService.getEntryById(userId);
		if(user != null){
			request.setAttribute("user", user);
			return "backPage/user/editRmark";
		}else{
			request.setAttribute("msg", "查询数据失败！");
			return getUserList(request, response);
		}
	}
	//发送邮件
	public void postMail(User user) {

			// 建立邮件消息,发送简单邮件和html邮件的区别
			MimeMessage mailMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = null;
			try {
				messageHelper = new MimeMessageHelper(
						mailMessage, true, "utf-8");
			Properties props = new Properties();
			//加载配置文件
			props.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));
	
			user.setStateCode(CommonUtils.uuid() + CommonUtils.uuid());
			String code = user.getStateCode();
			code = this.getServletContext().getContextPath() + "/userAction.do?method=activeUser&stateCode=" + code;
			//替换配置文件中的点位符
			String content = MessageFormat.format(props.getProperty("mail.content"),code);
			String to = user.getEmail();
			//替换配置文件中的点位符
			to = MessageFormat.format(props.getProperty("mail.to"),to);

			//设置接收者
			messageHelper.setTo(to);
			//设置发件人
			messageHelper.setFrom(props.getProperty("mail.from"));
			//设置邮箱的标题
			messageHelper.setSubject(props.getProperty("mail.subject"));
			// 设置邮箱的主体内容     true 表示启动HTML格式的邮件
				messageHelper.setText(content,true);
			} catch (Exception e) {
				throw new RuntimeException("邮箱发送失败！请检查邮箱地址是否正确！");
			}
			// 发送邮件
			javaMailSender.send(mailMessage);

			System.out.println("邮件发送成功..");
			
		
	}
	
	//校验用户登录的数据
	private void checkLoginParams(HttpServletRequest request,
			Map<String, String> errors,User user){
		checkName(errors, user);
		checkPassword(errors, user.getUserPasswd(), "passwordError");
		checkValidateCode(request, errors);
	}
	
	// 校验用户注册提交的数据
	private void checkRegisterParams(HttpServletRequest request,
			Map<String, String> errors,User user) {


		String userPasswd = user.getUserPasswd();
		String confirmPasswd = request.getParameter("confirmPassword");

		checkName(errors, user);

		checkEmailFormat(errors, user.getEmail());

		checkPassword(errors, userPasswd, "passwordError");
		checkPassword(errors, confirmPasswd, "confirmPwdError");

		if (!errors.containsValue("passwordError")
				&& !errors.containsValue("confirmPwdError")) {
			if (!userPasswd.equals(confirmPasswd)) {
				errors.put("msg", "二次输入的密码不一致");
			}
		}
		checkValidateCode(request, errors);
	}

	public void checkName(Map<String, String> errors, User user) {
		String userName = user.getUserName();

		if (null == userName || userName.trim().isEmpty()) {

			errors.put("nameError", "用户名为空");
		} else if (!userName.matches("[\\w\\u4E00-\\u9FBF]{2,9}")) {
			errors.put("nameError", "包含非法字符");
		}
	}

	public void checkEmailFormat(Map<String, String> errors, String email) {
		if (null == email || email.trim().isEmpty()) {

			errors.put("emailError", "邮箱为空");
		} else if (!email.matches("^[\\w]+@[\\w]+\\.[\\w]+$")) {
			errors.put("emailError", "邮箱格式错误");
		}
	}

	// 校验验证码
	public void checkValidateCode(HttpServletRequest request,
			Map<String, String> errors) {
		HttpSession session = request.getSession(false);
		String validateCodeA = null;
		if(session != null){
			validateCodeA = (String) session.getAttribute("validateCode");// 获取session中的验证码
		}
		String validateCodeB = request.getParameter("validateCode");

		if (validateCodeB == null || validateCodeB.trim().isEmpty()
				|| validateCodeB.equals("验证码")) {
			errors.put("vlidateError", "验证码为空");
		} else if (validateCodeA == null) {
			errors.put("vlidateError", "验证码失效");
		} else if (!validateCodeB.equalsIgnoreCase(validateCodeA)) {
			errors.put("vlidateError", "验证码错误");
		}
	}

	// 校验密码
	public void checkPassword(Map<String, String> errors, String password,
			String errorKey) {
		if (null == password || password.trim().isEmpty()) {
			errors.put(errorKey, "密码为空");
		} else if (password.length() < 5) {
			errors.put(errorKey, "密码的长度必须大于5");
		} else if (password.matches("\\W+")) {
			errors.put(errorKey, "密码只能是数字和英文字母的组合");
		}
	}

	// 生成验证码
	public void drawValidateImg(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");
			HttpSession session = request.getSession();
			int width = 110, height = 46;
			// outputVerifyImage(宽，高，输入流，验证码的个数);
			
			String validateCode = ValidateCodeUtils.outputVerifyImage(width,
					height, response.getOutputStream(), 4);
			
			session.setAttribute("validateCode", validateCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
