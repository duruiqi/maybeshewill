package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.Produce;
import com.scxh.yhzm.service.ProduceService;

public class ProduceAction extends BaseAction {
	private ProduceService<Produce> produceService;

	public void setProduceService(ProduceService<Produce> produceService) {
		this.produceService = produceService;
	}

	public String addProduce(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取到表单所有的项
		Map<String, Object> map = super.parseSpringUpload(request);
		Map<String, String> errorMsg = new HashMap<String, String>();

		// 封装的是上传文件的input的name值
		map.put("imageName", "image");
		
		checkFormParams(map, errorMsg);

		imgIsNull(map, errorMsg);
		String back = "/backPage/produce/add";

		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

		try {
			int backInfo = produceService.addEntry(map);

			if (backInfo > 0) {
				request.setAttribute("produceInfo", "添加成功！");
				return back;
			} else {
				throw new RuntimeException("添加失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("produceInfo", "添加失败！");
			return back;
		}

	}
	
	// 带分页查询所有数据
	public String getProduceList(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String curPage = request.getParameter("curPage");// 获取当前页的角标
		map.put("curPage", null == curPage ? 1 : new Integer(curPage));
		map.put("pageSize", countPerPage);// 一页固定要显示的纪录数
		try {
			PagingBean<Produce> pagingBean = produceService.getAllEntryFY(map);

			for (Produce p : pagingBean.getDataList()) {
				p.setProImages(request.getContextPath() + p.getProImages());
			}
			request.setAttribute("pagingBean", pagingBean);
			return "backPage/produce/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

	// 修改之前的获取操作
	public String getProduce(HttpServletRequest request,
			HttpServletResponse response) {
		String proId = request.getParameter("proId");

		try {
			if (null == proId) {
				request.setAttribute("msg", "提交ID为空,无法查询数据!");
				return getProduceList(request, response);
			}

			Produce produce = produceService.getEntryById(proId);

			if (null != produce) {
				request.setAttribute("form", produce);
				return "backPage/produce/edit";
			}

			return getProduceList(request, response);
			
		} catch (Exception e) {
			
			request.setAttribute("msg", "查询失败!");
			return getProduceList(request, response);
		}

	}
	public String getProduceDetail(HttpServletRequest request,
			HttpServletResponse response) {
		String proId = request.getParameter("proId");
		
		try {
			if (null == proId) {
				request.setAttribute("msg", "提交ID为空,无法查询数据!");
				return getProduceList(request, response);
			}
			
			Produce produce = produceService.getEntryById(proId);
			
			if (null != produce) {
				produce.setProImages(request.getContextPath() + produce.getProImages());
				
				request.setAttribute("form", produce);
				return "backPage/produce/produceDetail";
			}
			
			return getProduceList(request, response);
			
		} catch (Exception e) {
			
			request.setAttribute("msg", "查询失败!");
			return getProduceList(request, response);
		}
		
	}

	// 保存用户做的修改
	public String modProduce(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取到表单所有的项
		Map<String, Object> map = super.parseSpringUpload(request);
		Map<String, String> errorMsg = new HashMap<String, String>();

		CommonsMultipartFile uploadImg = (CommonsMultipartFile) map.get("image");

		if (uploadImg.getSize() > 0) {
			// 封装的是上传文件的input的name值
			map.put("imageName", "image");
			map.put("flag", "1");
			imgIsNull(map, errorMsg);
		}
		
		checkFormParams(map, errorMsg);

		String back = "/backPage/produce/edit";

		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

		try {
			int backInfo = produceService.modEntry(map);

			if (backInfo > 0) {
				request.setAttribute("projectOrderInfo", "修改成功！");
				return getProduceList(request, response);
			} else {
				throw new RuntimeException("修改失败！！");
			}

		} catch (Exception e) {
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			e.printStackTrace();
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
	public String delProduce(HttpServletRequest request,
			HttpServletResponse response) {
		if (null == BASE_PATH){
			BASE_PATH = getServletContext().getRealPath("/");
		}
		
		Map<String,Object> map = super.baseParamstoMap(request);
		
		map.put("basePath", BASE_PATH);

		try {
			Integer count = produceService.deleteEntry(map);
			if (null == count || count < 1)
				request.setAttribute("msg", "删除失败!");
		} catch (Exception e) {
			request.setAttribute("msg", "删除失败!");
			e.printStackTrace();
		}
		return getProduceList(request, response);
	}


	// 公共方法用于校验客户端上传的文件和请求传递的参数是否合理，并向super.parseSpringUpload(request)返回的map对象中封装少量的数据
	public void checkFormParams(Map<String, Object> map,
			Map<String, String> errorMsg) {

		String proName = (String) map.get("proName");

		String proType = (String) map.get("proType");

		String proCatogroy = (String) map.get("proCatogroy");

		String proMaterial = (String) map.get("proMaterial");
		
		String proArea = (String) map.get("proArea");
		String proSummary = (String) map.get("proSummary");
		String proDesc = (String) map.get("proDesc");
		String proParameter = (String) map.get("proParameter");


		checkStr(proName, errorMsg, "nameError", "名称不能为空");
		checkStr(proType, errorMsg, "pTypeError", "型号不能为空");
		checkStr(proCatogroy, errorMsg, "pCatogroyError", "类别不能为空");
		checkStr(proMaterial, errorMsg, "pMaterialError", "材质不能为空");
		
		checkStr(proArea, errorMsg, "proAreaError", "产地不能为空");
		checkStr(proSummary, errorMsg, "proSummaryError", "摘要不能为空");
		checkStr(proDesc, errorMsg, "proDescError", "详细描述不能为空");
		checkStr(proParameter, errorMsg, "proParamError", "产品的参数不能为空");

		BASE_PATH = getServletContext().getRealPath("/");
		map.put("basePath", BASE_PATH);
	}
	
	
	public String showProductUi(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String curPage = request.getParameter("curPage");// 获取当前页的角标
		map.put("curPage", null == curPage ? 1 : new Integer(curPage));
		map.put("pageSize", 6);// 一页固定要显示的纪录数
		try {
			PagingBean<Produce> pagingBean = produceService.getAllEntryFY(map);

			for (Produce p : pagingBean.getDataList()) {
				p.setProImages(request.getContextPath() + p.getProImages());
			}
			request.setAttribute("pagingBean", pagingBean);
			
			return "frontPage/product";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

}
