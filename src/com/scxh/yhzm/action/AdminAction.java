package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scxh.yhzm.po.Admin;
import com.scxh.yhzm.service.AdminService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.CookieUtil;
import com.scxh.yhzm.util.Encrypt;
import com.scxh.yhzm.util.ValidateCodeUtils;

public class AdminAction extends BaseAction {
	private AdminService adminService;
	
	//后台登录
	public String adminLogin(HttpServletRequest request,
			HttpServletResponse response){
		
		Map<String,String> errors = new HashMap<String,String>();;
		HttpSession session = null;
		Admin admin = null;
		
		Map<String,String> cookieInfo = new HashMap<String,String>();
		
		CookieUtil.readCookie(request, cookieInfo);
		
		if(cookieInfo.size() == 2){
			admin = CommonUtils.toBean(cookieInfo, Admin.class);
		}else{
			session = request.getSession();
			admin = CommonUtils.toBean(request.getParameterMap(),Admin.class);
			checkLogin(request, errors);
			session.removeAttribute("validateCode");//移除验证码的session，使提交之后验证码失效
			
			if(errors.size() > 0){
				request.setAttribute("form", admin);
				request.setAttribute("errors", errors);
				
				return "/backPage/login";
				
			}
			admin.setAdminPasswd(Encrypt.md5Encrypt(admin.getAdminPasswd()));
		}
		
			admin = adminService.login(admin);
			
			if (null != admin) {
				if("1".equals(request.getParameter("mark"))){//判断标志
					CookieUtil.saveCookie(response, admin);
				}
				
				request.getSession().setAttribute("account", admin);
				
				return "redirect:/backPage/main.jsp";
			}else{
				errors.put("loginError", "密码或用户名错误或者未被授权!");
				request.setAttribute("form", admin);
				request.setAttribute("errors", errors);
				return "/backPage/login";
			}
	}
	
	//后台退出登录
	public String quit(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/backPage/login.jsp";
	}
	//修改密码
	public String modAdminPasswd(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		
		Map<String,String> errors = new HashMap<String, String>();
		
		String oldPasswd = request.getParameter("oldPassword");
		String newPasswd = request.getParameter("newPassword");
		String confirm_passwd = request.getParameter("confirm_password");
		String  backAddr =  "/backPage/alterPassword";
		checkPassword(errors,oldPasswd,"oldPwdError");
		checkPassword(errors,newPasswd,"newPwdError");
		checkPassword(errors,confirm_passwd,"conrimPwdError");
		if(!newPasswd.equals(confirm_passwd)){
			errors.put("conrimPwdError", "二次密码不一致！");
		}
		
		if(errors.size() > 0){
			request.setAttribute("errorMsg", errors);
			return backAddr;
		}
		Admin admin = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		admin.setAdminPasswd(oldPasswd);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("admin", admin);
		paramMap.put("newPasswd", newPasswd);
		
		try{
			Integer num = adminService.modAdmin(paramMap);
			if(num > 0){
				session.removeAttribute("account");
				request.setAttribute("msg", "密码修改成功，请<a href='" + this.getServletContext().getContextPath() + 
							"/loginAction.do?method=quit' target='_parent'>重新登录</a>！");
				return "/backPage/message";
			}
		}
		catch(Exception e){
			errors.put("msg", e.getMessage());
			request.setAttribute("errorMsg", errors);
			return backAddr;
		}
		return backAddr;
	}

//校验管理员登录
	private void checkLogin(HttpServletRequest request,	Map<String, String> errors){
		
		Admin admin = CommonUtils.toBean(request.getParameterMap(),Admin.class);
		HttpSession session = request.getSession(false);
		
		String adminPasswd = admin.getAdminPasswd();
		String adminName = admin.getAdminName();
		String validateCodeA = null;
		if(session != null){
			
			validateCodeA = (String) session.getAttribute("validateCode");//获取session中的验证码
		}
		String validateCodeB = request.getParameter("validateCode");
		
		
		if(null == adminName || adminName.trim().isEmpty()){
			
			errors.put("nameError", "用户名为空");
		}else if(!adminName.matches("^[\\w\\u3400-\\u9FCC]++$")){
			errors.put("nameError", "包含非法字符");
		}
		
		checkPassword(errors, adminPasswd,"passwordError");
		
		if(validateCodeB == null || validateCodeB.trim().isEmpty() || validateCodeB.equals("验证码")){
			errors.put("vlidateError", "验证码为空");
		}else if(validateCodeA == null){
			errors.put("vlidateError", "验证码失效");
		}else if(!validateCodeB.equalsIgnoreCase(validateCodeA)){
			errors.put("vlidateError", "验证码错误");
		}
	}

	public void checkPassword(Map<String, String> errors,String password,String errorKey) {
		if(null == password || password.trim().isEmpty()){
			errors.put(errorKey, "密码为空");
		}else if(password.length()<5){
			errors.put(errorKey, "密码的长度必须大于5");
		}else if(password.matches("\\W+")){
			errors.put(errorKey, "密码只能是数字和英文字母的组合");
		}
	}

	//
	public void drawValidateImg(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			response.setContentType("image/jpeg");
			HttpSession session = request.getSession();
			int width = 110, height = 46;
			//outputVerifyImage(宽，高，输入流，验证码的个数);
			String validateCode = ValidateCodeUtils.outputVerifyImage(width, height, response.getOutputStream(), 4);
			session.setAttribute("validateCode", validateCode);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	//用户名回显数据时有中文乱码问题
}
