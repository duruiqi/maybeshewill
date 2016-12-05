package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.po.MainBanner;
import com.scxh.yhzm.service.MainBannerService;

public class MainBannerAction extends BaseAction {
	
	private MainBannerService<MainBanner> mainBannerService;


	public void setMainBannerService(MainBannerService<MainBanner> mainBannerService) {
		this.mainBannerService = mainBannerService;
	}
	
	//查询所有数据
	public String getMainBannerList(HttpServletRequest request,
				HttpServletResponse response) {
			List<MainBanner> mainBannerList = mainBannerService.getAllEntry2(null);
			for(MainBanner banner : mainBannerList){
				banner.setBannerImg(request.getContextPath() + banner.getBannerImg());
			}
			request.setAttribute("mainBannerList", mainBannerList);
			return "backPage/mainBanner/list";
	}
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public String addMainBanner(HttpServletRequest request,HttpServletResponse response){
		// 获取到表单所有的项
		Map<String, Object> map = super.parseSpringUpload(request);
		Map<String, String> errorMsg = new HashMap<String, String>();
		
		//封装的是上传文件的input的name值
		map.put("imageName", "image");
		if (null == BASE_PATH){
			BASE_PATH = getServletContext().getRealPath("/");
		}
		
		checkStr((String)map.get("timeslice"), errorMsg, "timesliceError", "广告滚动的参数为空");
		map.put("basePath", BASE_PATH);
		
		imgIsNull(map, errorMsg);
		
		String back = "/backPage/mainBanner/add";
		
		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

		try {
			int backInfo = mainBannerService.addEntry(map);

			if (backInfo > 0) {
				request.setAttribute("mainBannerMsg", "添加成功");
				return getMainBannerList(request, response);
			} else {
				throw new RuntimeException("添加失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("mainBannerMsg", "添加失败！");
			return back;
		}
	}
	
	public String getMainBanner(HttpServletRequest request,HttpServletResponse response){
		String bId = request.getParameter("bId");
		MainBanner banner = mainBannerService.getEntryById(bId);
		banner.setBannerImg(request.getContextPath() + banner.getBannerImg());
		request.setAttribute("form", banner);
		return "backPage/mainBanner/edit";
	}
	
	public String modMainBanner(HttpServletRequest request,
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
		checkStr((String)map.get("timeslice"), errorMsg, "timesliceError", "广告滚动的参数为空");
		map.put("basePath", BASE_PATH);
		String back = "/backPage/mainBanner/edit";
		
		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}
		
		try {
			int backInfo = mainBannerService.modEntry(map);

			if (backInfo > 0) {
				request.setAttribute("mainBannerMsg", "修改成功！");
				
				return getMainBannerList(request, response);
			} else {
				throw new RuntimeException("修改失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("mainBannerMsg", "修改失败！");
			return back;
		}

	}
	
	public String delMainBanner(HttpServletRequest request,
			HttpServletResponse response) {
		if (null == BASE_PATH){
			BASE_PATH = getServletContext().getRealPath("/");
		}
		
		Map<String,Object> map = super.baseParamstoMap(request);
		
		map.put("basePath", BASE_PATH);

		try {
			Integer count = mainBannerService.deleteEntry(map);
			if (null == count || count < 1)
				request.setAttribute("msg", "删除失败!");
		} catch (Exception e) {
			request.setAttribute("msg", "删除失败!");
			e.printStackTrace();
		}
		return getMainBannerList(request, response);
	}
}
