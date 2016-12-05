package com.scxh.yhzm.action;

import java.io.StringWriter;
import java.sql.Date;
import java.util.List;

import com.scxh.yhzm.po.Department;
import com.scxh.yhzm.po.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scxh.yhzm.service.EmployeeService;
import com.scxh.yhzm.service.DepartmentService;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class DepartmentAction extends MultiActionController {
	/* 将IDepartmentService交给springmvc管理？之后action的xml要加个property写这货 不然会报错500 空指针  */
	private DepartmentService departmentService;
	private EmployeeService<Employee> employeeService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public void setEmployeeService(EmployeeService<Employee> employeeService) {
		this.employeeService = employeeService;
	}

	/* 删除部门 已通过 后期应该会加个判断的把 */
	public String delDepartment(HttpServletRequest request,HttpServletResponse response) {

		String i = (String) request.getAttribute("dId");
		
			Integer j = departmentService.delDepartment(i);
			
			if (j > 0) {
				
				request.setAttribute("departmentInfo", "删除成功");

			}else{

				request.setAttribute("departmentInfo", "删除失败");
				
			}
			
			return getAllDepartment(request, response);
	}

	/* 增加部门  时间*/
	public String addDepartment(HttpServletRequest request,	HttpServletResponse response) {
		
		////////////////网页数据的获取///////////////////
		
		String dname = request.getParameter("dname");
		String principal = request.getParameter("principal");
		String found=request.getParameter("found");
		Date fOUND = Date.valueOf(found);
		Department department = new Department(dname, principal, fOUND);
		
		///////////////////////////判断是否添加成功///////////////
		///////////////////////////没添加成功的会炸。待解决。//////////
		//////////////////////////x年x月x日 已从前端解决问题发生的几率
		
		int i =departmentService.addDepartment(department);
		try {
			if(i>0){
				request.setAttribute("departmentInfo", "添加成功！");
				 return addddd(request, response);
			}else{
				throw new RuntimeException("添加失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("departmentInfo", "添加失败！");
			return "backPage/department/add";
		}
	}
	
	/*修改部门信息 */
	public String modDepartment(HttpServletRequest request,HttpServletResponse response) {
		
		/////////////////获取要修改的记录//////////////
		String dids=request.getParameter("dId");
		
		////////////////////获取更改过的数据部分//////////////////////////////
		
		Integer did = Integer.parseInt(dids);
		String dname = request.getParameter("dname");
		String principal = request.getParameter("principal");
		String found = request.getParameter("found");
		Date fOUND = Date.valueOf(found);
		Department department = new Department(did, dname, principal, fOUND);
		
		///////////////////////////判断是否添加成功///////////////
		
		
		try {
			int i =departmentService.modDepartment(department);
			if (i > 0) {
				request.setAttribute("departmentInfo", "修改成功！");
				return "/backPage/department/edit";
			} else {
				throw new RuntimeException("修改失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("departmentInfo", "修改失败！");
			return "/backPage/department/edit"; 
		}
	}
	/*查询单个记录。。修改专用。。。*/
	public String getDepartment(HttpServletRequest request,HttpServletResponse response) {
		
		String departmentId = request.getParameter("dId");
		
		Department department = departmentService.getDepartment(departmentId);
		
			request.setAttribute("department", department);
			
			return "/backPage/department/edit";
	}
	
	/*查询所有部门记录 */
	public String getAllDepartment(HttpServletRequest request,HttpServletResponse response){
		List<Department> deptList = departmentService.getAllDepartment();
		
		request.setAttribute("deptList", deptList);
		
		return "backPage/department/list";
	}
	
	public String addddd(HttpServletRequest request,HttpServletResponse response){
		return "backPage/department/add";
	}
	
	/* 这里写的是删除之前的查询该部门是否有员工的操作*/
	public String beforeDel(HttpServletRequest request,HttpServletResponse response){

		String i = request.getParameter("dId");
		// 这里加个判断 判断部门里面是否有员工
		List<Employee> emp = employeeService.getEmployeeByDid(i);
		int empsize = emp.size();
		if (empsize > 0) {
			request.setAttribute("departmentInfo", "此部门里面还有员工");
			return getAllDepartment(request, response);
		} else {
			request.setAttribute("dId", i);
			return delDepartment(request, response);
			}
		}
	/*似乎是要获取一个部门列表。。*/
	public void asynGetDepartmentList(HttpServletRequest request,HttpServletResponse response){
		List<Department> deptList = departmentService.getAllDepartment();
		try {
			StringWriter writer = new StringWriter();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(writer,deptList);
			String json = writer.toString();
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
