package com.scxh.yhzm.action;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.scxh.yhzm.po.ContactUs;
import com.scxh.yhzm.po.FirmInfo;
import com.scxh.yhzm.service.CompanyInfoService;
import com.scxh.yhzm.service.ContactUsService;

public class ContactUsAction extends BaseAction {
	private ContactUsService<ContactUs> contactUsService;
	
	private CompanyInfoService<FirmInfo> companyInfoService;

	public void setContactUsService(ContactUsService<ContactUs> contactUsService) {
		this.contactUsService = contactUsService;
	}

	public void setCompanyInfoService(
			CompanyInfoService<FirmInfo> companyInfoService) {
		this.companyInfoService = companyInfoService;
	}



	//查询所有数据
	public String getContactUsList(HttpServletRequest request,
				HttpServletResponse response) {
			List<ContactUs> contactUsList = contactUsService.getAllEntry2(null);
			request.setAttribute("contactUsList", contactUsList);
			return "backPage/contactUs/list";
		}
	
	public String getContactUs(HttpServletRequest request,HttpServletResponse response){
		String usId = request.getParameter("usId");
		ContactUs contactUs = contactUsService.getEntryById(usId);
		request.setAttribute("contactUs", contactUs);
		return "backPage/contactUs/edit";
	}
	
	public void aysnGetContactUs(HttpServletRequest request,HttpServletResponse response){
		
		String usId = request.getParameter("usId");
		ContactUs contactUs = contactUsService.getEntryById(usId);
		FirmInfo firmInfo = companyInfoService.getEntryByCoulmn(new FirmInfo(true));
		contactUs.setFirmInfo(firmInfo);
		request.setAttribute("contactUs", contactUs);
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.writeValue(writer,contactUs);
			String json = writer.toString();
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//保存用户做的修改
	public String modContactUs(HttpServletRequest request,
				HttpServletResponse response) {			

			// 获取到表单所有的项
			Map<String, Object> map = super.parseSpringUpload(request);
			Map<String, String> errorMsg = new HashMap<String, String>();
			
			checkFormParams(map, errorMsg);
			
			String back = "/backPage/companyInfo/edit";
			
			if (errorMsg.size() > 0) {// 向增加页面响应错误信息
				request.setAttribute("form", map);
				request.setAttribute("errorMsg", errorMsg);
				return back;
			}
			
			try {
				int backInfo = contactUsService.modEntry(map);

				if (backInfo > 0) {
					request.setAttribute("contactUsMsg", "修改成功！");
					
					return getContactUsList(request, response);
				} else {
					throw new RuntimeException("修改失败！！");
				}

			} catch (Exception e) {
				e.printStackTrace();
				errorMsg.put("msg", e.getMessage());
				request.setAttribute("form", map);
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("contactUsMsg", "修改失败！");
				return back;
			}

		}
		
	
		public void checkFormParams(Map<String, Object> map, Map<String, String> errorMsg) {
			
			
			String linkman = (String) map.get("linkman");
			
			String email = (String) map.get("email");
			
			String qq = (String) map.get("qq");
			
			String fax = (String) map.get("fax");
			
			String postcode = (String) map.get("postcode");
			
			String phone = (String) map.get("phone");
			
			String linkAddr = (String) map.get("linkAddr");
			
			checkStr(linkman, errorMsg, "linkmanError","联系人不能为空");
			checkStr(email, errorMsg,"emailError", "邮箱不能为空");
			checkStr(qq, errorMsg,"qqError", "QQ不能为空");
			checkStr(fax, errorMsg,"faxError", "传真不能为空");
			checkStr(postcode, errorMsg,"postcodeError", "邮编不能为空");
			checkStr(phone, errorMsg,"phoneError", "联系电话不能为空");
			checkStr(linkAddr, errorMsg,"linkAddrError", "联系地址不能为空");
		}
}
