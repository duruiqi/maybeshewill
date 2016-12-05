package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.ProjectOrder;
import com.scxh.yhzm.service.ProjectOrderService;
import com.scxh.yhzm.util.CommonUtils;

public class ProjectOrderAction extends BaseAction {
	private ProjectOrderService<ProjectOrder> projectOrderService;


	//带分页查询所有数据
	public String getProjectOrderList(HttpServletRequest request,
				HttpServletResponse response) {
			Map<String,Object> map = new HashMap<String, Object>();
			String curPage = request.getParameter("curPage");//获取当前页的角标
			
			map.put("curPage", null == curPage ? 1 : new Integer(curPage));
			
			map.put("pageSize", countPerPage);//一页固定要显示的纪录数
	
			try {
				PagingBean<ProjectOrder> pagingBean = projectOrderService.getAllEntryFY(map);

				for(ProjectOrder po : pagingBean.getDataList()){
					po.setProjectImg(request.getContextPath() + po.getProjectImg());
				}
				request.setAttribute("pagingBean", pagingBean);
				return "backPage/projectOrder/list";
			} catch (Exception e) {
				e.printStackTrace();
				return "500";
			}
		}
	
	//添加一条数据
		public String addProjectOrder(HttpServletRequest request,
				HttpServletResponse response) {

			// ------------------- feild -------------------
			// 获取到表单所有的项
			Map<String, Object> map = super.parseSpringUpload(request);
			Map<String, String> errorMsg = new HashMap<String, String>();
			
			//封装的是上传文件的input的name值
			map.put("imageName", "image");
			map.put("orderId", CommonUtils.uuid());
			commons(map, errorMsg);
			
			imgIsNull(map, errorMsg);
			String back = "/backPage/projectOrder/add";
			
			if (errorMsg.size() > 0) {// 向增加页面响应错误信息
				request.setAttribute("form", map);
				request.setAttribute("errorMsg", errorMsg);
				return back;
			}

			try {
				int backInfo = projectOrderService.addEntry(map);

				if (backInfo > 0) {
					request.setAttribute("projectOrderInfo", "添加成功！");
					return back;
				} else {
					throw new RuntimeException("添加失败！！");
				}

			} catch (Exception e) {
				e.printStackTrace();
				errorMsg.put("msg", e.getMessage());
				request.setAttribute("form", map);
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("projectOrderInfo", "添加失败！");
				return back;
			}

		}
		
		//修改之前的获取操作
		public String getProjectOrder(HttpServletRequest request,
				HttpServletResponse response) {
			String orderId = request.getParameter("orderId");
			HttpSession session = request.getSession();
			String redirect = "redirect:projectOrderAction.do?method=getProjectOrderList";
			if (null == orderId) {
				session.setAttribute("msg", "提交ID为空,无法查询数据!");
				return redirect;
			}

			try {

				ProjectOrder projectOrder = projectOrderService.getEntryById(orderId);
				
				if (null != projectOrder) {
					request.setAttribute("form", projectOrder);
					return "backPage/projectOrder/edit";
				}
				
				return redirect;
			} catch (Exception e) {
				session.setAttribute("msg", "查询id为" + orderId + "数据失败!");
				e.printStackTrace();
				return redirect;
			}

		}
		
		
		
		public String getProjectDetail(HttpServletRequest request,
				HttpServletResponse response){
			String orderId = request.getParameter("orderId");
			HttpSession session = request.getSession();
			String redirect = "redirect:projectOrderAction.do?method=getProjectOrderList";
			if (null == orderId) {
				session.setAttribute("msg", "提交ID为空,无法查询数据!");
				return redirect;
			}

			try {

				ProjectOrder projectOrder = projectOrderService.getEntryById(orderId);
				
				if (null != projectOrder) {
					projectOrder.setProjectImg(request.getContextPath() + projectOrder.getProjectImg());
					request.setAttribute("form", projectOrder);
					return "backPage/projectOrder/detail";
				}
				
				return redirect;
			} catch (Exception e) {
				session.setAttribute("msg", "查询id为" + orderId + "数据失败!");
				e.printStackTrace();
				return redirect;
			}
		}
		//保存用户做的修改
		public String modProjectOrder(HttpServletRequest request,
				HttpServletResponse response) {			

			// ------------------- feild -------------------
			// 获取到表单所有的项
			Map<String, Object> map = super.parseSpringUpload(request);
			Map<String, String> errorMsg = new HashMap<String, String>();
			
			CommonsMultipartFile uploadImg = (CommonsMultipartFile)map.get("image");
			
			if(uploadImg.getSize() > 0){
				//封装的是上传文件的input的name值
				map.put("imageName", "image");
				//封装一个ProjectOrder的成员变量名到map的一个键中
				map.put("imageAddr", "projectImg");
				
				map.put("flag", "1");
				imgIsNull(map, errorMsg);
			}
			commons(map, errorMsg);
			
			String back = "/backPage/projectOrder/edit";
			
			if (errorMsg.size() > 0) {// 向增加页面响应错误信息
				request.setAttribute("form", map);
				request.setAttribute("errorMsg", errorMsg);
				return back;
			}
			
			try {
				int backInfo = projectOrderService.modEntry(map);

				if (backInfo > 0) {
					request.setAttribute("projectOrderInfo", "修改成功！");
					return getProjectOrderList(request, response);
				} else {
					throw new RuntimeException("修改失败！！");
				}

			} catch (Exception e) {
				e.printStackTrace();
				errorMsg.put("msg", e.getMessage());
				request.setAttribute("form", map);
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("projectOrderInfo", "修改失败！");
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
		public String delProjectOrder(HttpServletRequest request,
				HttpServletResponse response) {
			if (null == BASE_PATH)
				BASE_PATH = getServletContext().getRealPath("/");
			
			String orderId = request.getParameter("orderId");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			
			map.put("basePath", BASE_PATH);
			
			try {
				Integer count = projectOrderService.deleteEntry(map);
				if (null == count || count < 1)
					request.setAttribute("msg", "删除失败!");
			} catch (Exception e) {
				request.setAttribute("msg", "删除失败!");
				e.printStackTrace();
			}
			
			return getProjectOrderList(request, response);
		}

	
//////////////////////////////////////////////////////////////////////////////////////////////

		//公共方法用于校验客户端上传的文件和请求传递的参数是否合理，并向super.parseSpringUpload(request)返回的map对象中封装少量的数据
		public void commons(Map<String, Object> map, Map<String, String> errorMsg) {
			
			String projectName = (String) map.get("projectName");
			
			String projectType = (String) map.get("projectType");
			
			String projectAddr = (String) map.get("projectAddr");
			
			String projectPrice = (String) map.get("projectPrice");
			
			String projectDetail = (String) map.get("projectDetail");
			projectDetail = projectDetail.trim();
			String startDate = (String) map.get("startDate");
			
			String endDate = (String) map.get("endDate");
			
			String ename = (String) map.get("ename");
			
			String customerUnit = (String) map.get("customerUnit");
						
			if(projectName == null || projectName.trim().isEmpty()){
				errorMsg.put("pNameError", "工程名为空");
			}else if(projectName.length() > 20){
				errorMsg.put("pNameError", "工程名不可超过二十个字");
			}
			
			if(projectType == null || projectType.trim().isEmpty()){
				errorMsg.put("pTypeError", "工程类别为空");
			}
			
			if(projectAddr == null || projectAddr.trim().isEmpty()){
				errorMsg.put("pAddrError", "工程地址为空");
			}
			
			if(projectPrice == null || projectPrice.trim().isEmpty()){
				errorMsg.put("pPriceError", "工程定价空");
			}
			if(projectDetail == null || projectDetail.trim().isEmpty()){
				errorMsg.put("pDetailError", "工程详细空");
			}
			if(ename == null || ename.trim().isEmpty()){
				errorMsg.put("enameError", "负责人不能为空");
			}
			if(customerUnit == null || customerUnit.trim().isEmpty()){
				errorMsg.put("cUnitError", "客户单位不能为空");
			}
			
			if(startDate == null || startDate.trim().isEmpty() || "开始时间".equals(startDate)){
				errorMsg.put("startDateError", "请选择开始日期");
			}
			if(endDate == null || endDate.trim().isEmpty() || "结束时间".equals(endDate)){
				errorMsg.put("endDateError", "请选择结束日期");
			}
			
			BASE_PATH = getServletContext().getRealPath("/");
			map.put("basePath", BASE_PATH);
			// ---------------------------------------------------------------------
		}

		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

		public void setProjectOrderService(ProjectOrderService<ProjectOrder> projectOrderService) {
			this.projectOrderService = projectOrderService;
		}
}
