package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.po.FirmInfo;
import com.scxh.yhzm.service.CompanyInfoService;
import com.scxh.yhzm.util.CommonUtils;

public class CompanyInfoAction extends BaseAction {
	private CompanyInfoService<FirmInfo> companyInfoService;


	public void setCompanyInfoService(
			CompanyInfoService<FirmInfo> companyInfoService) {
		this.companyInfoService = companyInfoService;
	}

	public String addFirmInfo(HttpServletRequest request,HttpServletResponse response){
		// ------------------- feild -------------------
		// 获取到表单所有的项
		Map<String, Object> map = super.parseSpringUpload(request);
		Map<String, String> errorMsg = new HashMap<String, String>();
		
		//封装的是上传文件的input的name值
		map.put("imageName", "image");
		map.put("firmId", CommonUtils.uuid());
		
		checkFormParams(map, errorMsg);
		
		imgIsNull(map, errorMsg);
		
		String back = "/backPage/companyInfo/add";
		
		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

		try {
			int backInfo = companyInfoService.addEntry(map);

			if (backInfo > 0) {
				request.setAttribute("companyInfoMsg", "添加成功");
				return getFirmInfoList(request, response);
			} else {
				throw new RuntimeException("添加失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("companyInfoMsg", "添加失败！");
			return back;
		}

		
	}

	//查询所有数据
	public String getFirmInfoList(HttpServletRequest request,
				HttpServletResponse response) {
	
			try {
			 List<FirmInfo> firmList = companyInfoService.getAllEntry2(null);
			 request.setAttribute("firmList", firmList);
				return "backPage/companyInfo/list";
			} catch (Exception e) {
				e.printStackTrace();
				return "500";
			}
		}
	public String showUi(HttpServletRequest request,
			HttpServletResponse response) {
		FirmInfo firmInfo = companyInfoService.getEntryByCoulmn(new FirmInfo(true));
		String[] strs = firmInfo.getImgAddrs();
		for(int i = 0;i < strs.length;i++){
			strs[i] = request.getContextPath() + strs[i];
		}
		firmInfo.setImgAddrs(strs);
		firmInfo.setIntroduce(firmInfo.getIntroduce().replaceAll(" ", ""));
		request.setAttribute("firm", firmInfo);
		return "/frontPage/aboutUs";
	}
	
	public String getFirmInfo(HttpServletRequest request,HttpServletResponse response){
		String firmId = request.getParameter("firmId");
		String flag = request.getParameter("flag");
		FirmInfo firmInfo = companyInfoService.getEntryById(firmId);
		
		if(firmInfo.getImgAddrs() != null){
			addContextPath(firmInfo);
		}
		request.setAttribute("firm", firmInfo);
		
		if("detail".equals(flag)){
			return "backPage/companyInfo/detail";
			
		}else if("updateImg".equals(flag)){
			return "backPage/companyInfo/manageImg";
		}
		
		return "backPage/companyInfo/edit";
	}

	public void addContextPath(FirmInfo firmInfo) {
		String[] strs = firmInfo.getImgAddrs();
		
		for(int i = 0;i < strs.length;i++){
			strs[i] = this.getServletContext().getContextPath() + strs[i];
		}
		
		firmInfo.setImgAddrs(strs);
	}
	
		//保存用户做的修改
		public String modFirmInfo(HttpServletRequest request,
				HttpServletResponse response) {			

			// ------------------- feild -------------------
			// 获取到表单所有的项
			Map<String, Object> map = super.parseSpringUpload(request);
			Map<String, String> errorMsg = new HashMap<String, String>();
			
			CommonsMultipartFile uploadImg = (CommonsMultipartFile)map.get("image");
			
			if(uploadImg.getSize() > 0){
				//封装的是上传文件的input的name值
				map.put("imageName", "image");
				map.put("flag", "1");
				imgIsNull(map, errorMsg);
			}
			checkFormParams(map, errorMsg);
			
			String back = "/backPage/companyInfo/edit";
			
			if (errorMsg.size() > 0) {// 向增加页面响应错误信息
				request.setAttribute("firm", map);
				request.setAttribute("errorMsg", errorMsg);
				return back;
			}
			
			try {
				int backInfo = companyInfoService.modEntry(map);

				if (backInfo > 0) {
					request.setAttribute("firmInfoMsg", "修改成功！");
					
					return getFirmInfoList(request, response);
				} else {
					throw new RuntimeException("修改失败！！");
				}

			} catch (Exception e) {
				e.printStackTrace();
				errorMsg.put("msg", e.getMessage());
				request.setAttribute("firm", map);
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("firmInfoMsg", "修改失败！");
				return back;
			}

		}
		
		/**
		 * 在查看页面上执行删除(查看页面相当于modBefore页面)
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		public String delFirmInfo(HttpServletRequest request,
				HttpServletResponse response) {
			if (null == BASE_PATH)
				BASE_PATH = getServletContext().getRealPath("/");
			
			String firmId = request.getParameter("firmId");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("firmId", firmId);
			
			map.put("basePath", BASE_PATH);
			
			try {
				Integer count = companyInfoService.deleteEntry(map);
				if (null == count || count < 1)
					request.setAttribute("msg", "删除失败!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "删除失败!");
			}
			
			return getFirmInfoList(request, response);
		}
	
		public String addFirmImg(HttpServletRequest request,HttpServletResponse response){
			Map<String,String> errorMsg = new HashMap<String,String>();
			Map<String,Object> map = super.parseSpringUpload(request);
			imgIsNull(map, errorMsg);
			
			if(errorMsg.size() > 0){
				return "/";
			}
			return "";
		}
		public String delFirmImg(HttpServletRequest request,HttpServletResponse response){
			String[] delImgs = request.getParameterValues("delImgStr");
			String firmId = request.getParameter("firmId");
			for(int i = 0; i < delImgs.length;i++){
				int index = delImgs[0].lastIndexOf("/");
				delImgs[0] = delImgs[0].substring(index + 1, delImgs[0].length());
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			BASE_PATH = getServletContext().getRealPath("/");
			map.put("basePath", BASE_PATH);
			map.put("firmId", firmId);
			map.put("imgAddrs", delImgs);
			Integer back = companyInfoService.delFrimImg(map);
			if(back > 0){
				
				return getFirmInfo(request, response);
			}
			
			return "500";
		}
//////////////////////////////////////////////////////////////////////////////////////////////

		//公共方法用于校验客户端上传的文件和请求传递的参数是否合理，并向super.parseSpringUpload(request)返回的map对象中封装少量的数据
		public void checkFormParams(Map<String, Object> map, Map<String, String> errorMsg) {
			
			String firmName = (String) map.get("firmName");
			
			String loginFund = (String) map.get("loginFund");
			
			String introduce = (String) map.get("introduce");
			
			introduce = introduce.trim();
			
			String firmAddr = (String) map.get("firmAddr");
			
			String beiAnInfo = (String) map.get("beiAnInfo");
			
			String founds = (String) map.get("founds");
			
			
			
			checkStr(firmName, errorMsg, "nameError","公司名不能为空");
			checkStr(loginFund, errorMsg,"fundError", "注册资金不能为空");
			checkStr(introduce, errorMsg,"introduceError", "公司介绍不能为空");
			checkStr(firmAddr, errorMsg,"addrError", "公司地址不能为空");
			checkStr(beiAnInfo, errorMsg,"beiAnError", "备案信息不能为空");
						
			
			if(founds == null || founds.trim().isEmpty() || "成立时间".equals(founds)){
				errorMsg.put("foundsError", "请选择成立日期");
			}
			
			BASE_PATH = getServletContext().getRealPath("/");
			map.put("basePath", BASE_PATH);
			// ---------------------------------------------------------------------
		}
		
}
