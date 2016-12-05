package com.scxh.yhzm.action;
/**
 * @author HPH
 * 遇到了找不到getter的错误 好拙计
 * 
 *  找不到geter解决了 然后找不到id了。。。卧槽
 *  似乎employees表设计的有问题 
 *  然后的话是删除可能也有问题。。
 *  接下来把增加写一下吧。。。
 */



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Spring;

import com.scxh.yhzm.po.Employee;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.Salary;
import com.scxh.yhzm.service.EmployeeService;
import com.scxh.yhzm.service.SalaryService;

public class SalaryAction  extends BaseAction{
	
	private SalaryService<Salary> salaryService;
	private EmployeeService<Employee> employeeService;
	
	public SalaryService<Salary> getSalaryService() {
		return salaryService;
	}

	public void setSalaryService(SalaryService<Salary> salaryService) {
		this.salaryService = salaryService;
	}
	
	public EmployeeService<Employee> getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService<Employee> employeeService) {
		this.employeeService = employeeService;
	}

	public String getSalaryList(HttpServletRequest request,HttpServletResponse response){
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		String curPage = request.getParameter("curPage");//获取当前页的角标
		
		map.put("curPage", null == curPage ? 1 : new Integer(curPage));
		
		map.put("pageSize", countPerPage);//一页固定要显示的纪录数

		try {
			//这份数据是显示列表用的
			PagingBean<Salary> pagingBean = salaryService.getAllEntryFY(map);
			//这份是找到员工名字用的
			List<Employee> emList = employeeService.getAllEmployee();
			
			request.setAttribute("emplist", emList);
			
			request.setAttribute("pagingBean", pagingBean);
			
			
			return "backPage/salary/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
		
	}
	//增加salary
		public String addSalary(HttpServletRequest request ,HttpServletResponse response){
			
			Map<String, Object> map = super.parseSpringUpload(request);
			
			
			
			
			return null;
		}
		
		
		public String getSalary(HttpServletRequest request ,HttpServletResponse response){
			
			String id = request.getParameter("sid");
			
			Salary salary  = salaryService.getEntryById(id);
			
			
			if(salary!=null){
				
				request.setAttribute("salary", salary);
				
				return "backPage/salary/edit";
				
			}else{
				return getSalaryList(request,response);
			}
		}
		//修改salary
		public String updateSalary(HttpServletRequest request,HttpServletResponse response){
			//获取所有的请求 之后封装成map 然后发现之前的写的有问题啊。。。用错方法了 真他妈坑 不过似乎还是佩服那些前辈了
			Map<String, Object> map =  super.baseParamstoMap(request);
			
			String id = (String) map.get("eid");
			
			
			Employee emp = employeeService.getEntryById(id);
			
			map.put("emp", emp);
			
			Integer i =  salaryService.modEntry(map);
			
			
			if(i>0){
				request.setAttribute("salinfo", "修改成功");
			}else{
				request.setAttribute("salinfo", "修改失败");
				return "500";
			}
			
			return getSalaryList(request,response);
			
		}
}
