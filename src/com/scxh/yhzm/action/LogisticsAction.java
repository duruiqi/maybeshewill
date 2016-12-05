package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.exception.ParamsException;
import com.scxh.yhzm.po.Employee;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.Purchase;
import com.scxh.yhzm.service.EmployeeService;
import com.scxh.yhzm.service.LogisticsService;
import com.scxh.yhzm.util.CommonUtils;

public class LogisticsAction extends BaseAction {

	private LogisticsService<Purchase> logisticsService;
	private EmployeeService<Employee> employeeService;
	
	public void setLogisticsService(LogisticsService<Purchase> logisticsService) {
		this.logisticsService = logisticsService;
	}

	public void setEmployeeService(EmployeeService<Employee> employeeService) {
		this.employeeService = employeeService;
	}

	public String getPurchaseList(HttpServletRequest request,HttpServletResponse response){
		
		Map<String,Object> map = new HashMap<String, Object>();
		String curPage = request.getParameter("curPage");//获取当前页的角标
		
		map.put("curPage", null == curPage ? 1 : new Integer(curPage));
		
		map.put("pageSize", countPerPage);//一页固定要显示的纪录数
		
		PagingBean<Purchase> pagingBean = logisticsService.getAllEntryFY(map);
		
		request.setAttribute("pagingBean", pagingBean);
		return "backPage/logistics/list";
	}
	
	public String addPurchase(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> map = super.baseParamstoMap(request);
		Map<String, String> errorMsg = new HashMap<String, String>();
		
		//封装的是上传文件的input的name值
		map.put("purId", CommonUtils.uuid());
		
		checkFormParams(map, errorMsg);
		
		String back = "/backPage/logistics/add";
		
		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

		try {
			
			int backInfo = logisticsService.addEntry(map);

			if (backInfo > 0) {
				request.setAttribute("purchaseMsg", "添加成功");
				return getPurchaseList(request, response);
			} else {
				throw new RuntimeException("添加失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("purchaseMsg", "添加失败！");
			return back;
		}

		
	}

	public String getPurchase(HttpServletRequest request,
				HttpServletResponse response) {
				String purId = request.getParameter("purId");
				Purchase purchase = logisticsService.getEntryById(purId);
				request.setAttribute("form", purchase);
				return "backPage/logistics/edit";
	}
	
	public String modPurchase(HttpServletRequest request,HttpServletResponse response){
		
		Map<String,Object> map = super.baseParamstoMap(request);
		Map<String,String> errorMsg = new HashMap<String,String>();
		try{
			checkFormParams(map, errorMsg);
			if(errorMsg.size() > 0){
				throw new ParamsException();
			}
			Integer back = logisticsService.modEntry(map);
			if(back > 0){
				return getPurchaseList(request, response);
			}else{
				throw new ParamsException();
			}
		}catch(Exception e){
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("form", map);
			return "backPage/logistics/edit";
		}
		
	}
	
	public String delPurchase(HttpServletRequest request,
				HttpServletResponse response) {
			
			try {
				Integer count = logisticsService.deleteEntry(super.baseParamstoMap(request));
				if (count < 1)
					request.setAttribute("msg", "删除失败!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "删除失败!");
			}
			
			return getPurchaseList(request, response);
		}
		
	public void exportExcel(HttpServletRequest request,
					HttpServletResponse response) {
			try {
				//1、查找用户列表
			 List<Purchase>	purchaseList = logisticsService.getAllEntry2(null);
				//2、导出
				response.setContentType("application/x-execl");
				//解决了中文乱码问题
				response.setHeader("Content-Disposition", "attachment;filename=" + new String("采购订单明细.xls".getBytes(), "ISO-8859-1"));
				ServletOutputStream outputStream = response.getOutputStream();
				logisticsService.exportExcel(purchaseList, outputStream);
				if(outputStream != null){
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 
	 public String importExcel(HttpServletRequest request,
			 HttpServletResponse response) {
		 try{
			Map<String,Object> map = super.parseSpringUpload(request);
			CommonsMultipartFile excleFile =  (CommonsMultipartFile) map.get("excleFile");
//			excleFile
			//1、获取excel文件
			if(excleFile != null){
				//是否是excel
				if(excleFile.getOriginalFilename().matches("^.+\\.(?i)((xls)|(xlsx))$")){
					//2、导入
					logisticsService.importExcel(excleFile);
					request.setAttribute("msg", "导入成功");
				}else{
					request.setAttribute("msg", "不支持的文件类型或文件为空");
				}
			}
		 }catch(Exception e){
			 request.setAttribute("msg", e.getMessage());
			 return getPurchaseList(request, response);
		 }
		 
		return getPurchaseList(request, response);
	 }
	 public String getSsEmployee(HttpServletRequest request,
			 HttpServletResponse response){
		 request.setAttribute("empList", employeeService.getSsEmployee(new String[]{"宿管","食堂经理"}));
		 return "backPage/logistics/ssList";
	 }

		//公共方法用于校验客户端上传的文件和请求传递的参数是否合理，并向super.parseSpringUpload(request)返回的map对象中封装少量的数据
		public void checkFormParams(Map<String, Object> map, Map<String, String> errorMsg) {
			
			String materialName = (String) map.get("materialName");
			
			String materialType = (String) map.get("materialType");
			
			String purCount = (String) map.get("purCount");
			
			String purPrice = (String) map.get("purPrice");
			
			String isPay = (String) map.get("isPay");
			
			String payMethod = (String) map.get("payMethod");
			
			String purDate = (String) map.get("purDate");
			
			String dname = (String) map.get("dname");
			
			checkStr(materialName, errorMsg, "mNameError","材料名称不能为空");
			checkStr(materialType, errorMsg,"mTypeError", "材料材质不能为空");
			checkStr(purCount, errorMsg,"purCountError", "采购数量不能为空");
			checkStr(purPrice, errorMsg,"purPriceError", "采购价格不能为空");
			checkStr(isPay, errorMsg,"isPayError", "是否支付请选择");
			checkStr(payMethod, errorMsg,"payMethodError", "支付方式请选择");
						
			
			if(purDate == null || purDate.trim().isEmpty() || "采购时间".equals(purDate)){
				errorMsg.put("purDateError", "请选择采购日期");
			}
			if(dname == null || dname.trim().isEmpty()){
				errorMsg.put("dIdError", "请选择申购的部门");
			}
			
			BASE_PATH = getServletContext().getRealPath("/");
			map.put("basePath", BASE_PATH);
		}
		
}
