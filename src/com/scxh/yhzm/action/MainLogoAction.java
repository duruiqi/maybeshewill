package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.po.MainLogo;
import com.scxh.yhzm.service.MainLogoService;

public class MainLogoAction extends BaseAction {
	
	private MainLogoService<MainLogo> mainLogoService;


	public void setMainLogoService(MainLogoService<MainLogo> mainLogoService) {
		this.mainLogoService = mainLogoService;
	}
	
	//查询所有数据
	public String getMainLogoList(HttpServletRequest request,
				HttpServletResponse response) {
			List<MainLogo> mainLogoList = mainLogoService.getAllEntry2(null);
			for(MainLogo logo : mainLogoList){
				logo.setMlogo(request.getContextPath() + logo.getMlogo());
			}
			request.setAttribute("mainLogoList", mainLogoList);
			return "backPage/mainLogo/list";
	}
	
	public String addMainLogo(HttpServletRequest request,HttpServletResponse response){
		// 获取到表单所有的项
		Map<String, Object> map = super.parseSpringUpload(request);
		Map<String, String> errorMsg = new HashMap<String, String>();
		
		//封装的是上传文件的input的name值
		map.put("imageName", "image");
		if (null == BASE_PATH){
			BASE_PATH = getServletContext().getRealPath("/");
		}
		
		map.put("basePath", BASE_PATH);
		
		imgIsNull(map, errorMsg);
		
		String back = "/backPage/mainLogo/add";
		
		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

		try {
			int backInfo = mainLogoService.addEntry(map);

			if (backInfo > 0) {
				request.setAttribute("mainLogoMsg", "添加成功");
				return getMainLogoList(request, response);
			} else {
				throw new RuntimeException("添加失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("mainLogoMsg", "添加失败！");
			return back;
		}
	}
	
	public String getMainLogo(HttpServletRequest request,HttpServletResponse response){
		String mainId = request.getParameter("mainId");
		MainLogo mainLogo = mainLogoService.getEntryById(mainId);
		mainLogo.setMlogo(request.getContextPath() + mainLogo.getMlogo());
		request.setAttribute("form", mainLogo);
		return "backPage/mainLogo/edit";
	}
	
	public String modMainLogo(HttpServletRequest request,
			HttpServletResponse response) {			

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
		
		String back = "/backPage/mainLogo/edit";
		
		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}
		
		try {
			int backInfo = mainLogoService.modEntry(map);

			if (backInfo > 0) {
				request.setAttribute("mainLogoMsg", "修改成功！");
				
				return getMainLogoList(request, response);
			} else {
				throw new RuntimeException("修改失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("mainLogoMsg", "修改失败！");
			return back;
		}

	}
	
	public String delMainLogo(HttpServletRequest request,
			HttpServletResponse response) {
		if (null == BASE_PATH){
			BASE_PATH = getServletContext().getRealPath("/");
		}
		
		Map<String,Object> map = super.baseParamstoMap(request);
		
		map.put("basePath", BASE_PATH);

		try {
			Integer count = mainLogoService.deleteEntry(map);
			if (null == count || count < 1)
				request.setAttribute("msg", "删除失败!");
		} catch (Exception e) {
			request.setAttribute("msg", "删除失败!");
			e.printStackTrace();
		}
		return getMainLogoList(request, response);
	}
}
