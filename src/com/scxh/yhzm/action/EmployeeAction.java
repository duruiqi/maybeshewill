package com.scxh.yhzm.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.scxh.yhzm.po.Department;
import com.scxh.yhzm.po.Employee;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.service.DepartmentService;
import com.scxh.yhzm.service.EmployeeService;

public class EmployeeAction  extends BaseAction{
	private EmployeeService<Employee> employeeService;
	private DepartmentService departmentService;
	
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public EmployeeService<Employee> getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService<Employee> employeeService) {
		this.employeeService = employeeService;
	}
	
//	原来是给部门入口的员工管理用的
//	public String getEmployeeByDid(HttpServletRequest request,HttpServletResponse response){
//		
//		String did = request.getParameter("dId");
//		List<Employee> employee =employeeService.getEmployeeByDid(did);
//		
//		request.setAttribute("emplist", employee);
//		return "/backPage/department/emplist";
//		
//	}
	
	
	
	//带分页查询所有数据
	public String getEmployeeList(HttpServletRequest request,HttpServletResponse response) {
		
			Map<String,Object> map = new HashMap<String, Object>();
			
			String curPage = request.getParameter("curPage");//获取当前页的角标
			
			map.put("curPage", null == curPage ? 1 : new Integer(curPage));
			
			map.put("pageSize", countPerPage);//一页固定要显示的纪录数
	
			try {
				//这份数据是显示列表用的
				PagingBean<Employee> pagingBean = employeeService.getAllEntryFY(map);
				//这份找所属部门用的
				List<Department> department = departmentService.getAllDepartment();
				//这份比较找上级用的
				
				request.setAttribute("departList", department);
				
				request.setAttribute("pagingBean", pagingBean);
				
				
				return "backPage/employee/list";
			} catch (Exception e) {
				e.printStackTrace();
				return "500";
			}
		}
	//删除操作
	public String delEmployee(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String eid = request.getParameter("eid");
		
		map.put("eid", eid);
		
		try {
			Integer count = employeeService.deleteEntry(map);
			if (count>0)
				request.setAttribute("Employeeinfo", "删除成功!");
		} catch (Exception e) {
			request.setAttribute("Employeeinfo", "删除失败!");
			e.printStackTrace();
		}
		return getEmployeeList(request, response);
		
	}
	//修改之前的查询操作
	public String getEmployee(HttpServletRequest request,HttpServletResponse response){
		
		String eids = request.getParameter("eid");
		Integer eid = Integer.parseInt(eids);
		Employee emp =employeeService.getEntryById(eid);
		List<Department> departList=departmentService.getAllDepartment();
		
		if(emp!=null){
			request.setAttribute("emp", emp);
			request.setAttribute("departList", departList);
			return "/backPage/employee/edit";
		}else{
			return getEmployeeList(request, response);
		}
		
	}
	//修改操作 
	public String modEmployee(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> map = super.parseSpringUpload(request);
		
		String dids = (String) map.get("did");
		
		Department depart = departmentService.getDepartment(dids);
		
		map.put("depart", depart);
		
		Integer i = employeeService.modEntry(map);
		if(i>0){
			request.setAttribute("Employeeinfo", "修改成功");
			return getEmployeeList(request, response);
			
		}else{
			request.setAttribute("Employeeinfo", "修改失败！");
			return getEmployeeList(request, response);
		}
		
	}
	
	//添加员工
	public String addEmployee(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> map = super.parseSpringUpload(request);
		
		String dids = (String) map.get("did");
		
		Department depart = departmentService.getDepartment(dids);
		
		map.put("depart", depart);
		
		Integer i = employeeService.addEntry(map);
		if(i>0){
			request.setAttribute("Employeeinfo", "添加成功");
			return addBefore(request, response);
			
		}else{
			request.setAttribute("Employeeinfo", "添加失败！");
			return addBefore(request, response);
		}
		
	}
	
	public String addBefore(HttpServletRequest request,HttpServletResponse response){
		
		List<Department> departList=departmentService.getAllDepartment();
		
		request.setAttribute("departList", departList);
		
		return "/backPage/employee/add";
	}
	
}
