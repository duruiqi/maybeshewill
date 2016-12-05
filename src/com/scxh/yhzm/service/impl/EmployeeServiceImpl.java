package com.scxh.yhzm.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.EmployeeMapper;
import com.scxh.yhzm.po.Department;
import com.scxh.yhzm.po.Employee;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.service.EmployeeService;
import com.scxh.yhzm.util.CommonUtils;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService<Employee>{
	private EmployeeMapper<Employee> employeeMapper;

	public void setEmployeeMapper(EmployeeMapper<Employee> employeeMapper) {
		this.employeeMapper = employeeMapper;
	}
	@Override
	public Employee getEntryById(Serializable id) {
		
		return employeeMapper.getEntryById(id);
	}

	@Override
	public Employee getEntryByName(Serializable ename) {
		return employeeMapper.getEntryByName(ename);
	}
	@Override
	public List<Employee> getSsEmployee(String[] params) {
		return employeeMapper.getSsEmployee(params);
	}

	@Override
	public PagingBean<Employee> getAllEntryFY(Map<String, Object> map) {
		
		Integer curPage = (Integer) map.get("curPage");
		Integer pageSize = (Integer) map.get("pageSize");
		//		Integer totalRecord = EmployeeMapper.selectCountEntry();//原来的结果
		Integer totalRecord = employeeMapper.selectCountEntry(null);
		
		Integer startIndex = (curPage-1)*pageSize;
		map.put("startIndex", startIndex);//select * from tab_name limit startIndex,pageSize;
		List<Employee> pOrderList= employeeMapper.getALLEntryFY(map);
		
		PagingBean<Employee> pagingBean = new PagingBean<Employee>(totalRecord,curPage,pageSize,pOrderList);
		return pagingBean;
	}
	
	@Override
	public Integer deleteEntry(Map<String,Object> map) {
		
		String eids = (String) map.get("eid");
		
		String[] eid = eids.split(",");
		
		return employeeMapper.deleteEntry(eid);
		
	}
	
	public Employee getEntryById(Integer id){
		
		return employeeMapper.getEntryById(id);
	}
	
	public Integer modEntry(Map<String, Object> map){
		//月度惊悚片 "找不到的对象"
		Department depart = (Department) map.get("depart");
		
		Employee emp = CommonUtils.toBean(map,Employee.class);
		
		emp.setDepartment(depart);
		
		Integer i =employeeMapper.updateEntry(emp);
		
		return i;
		
	}
	
	public Integer addEntry(Map<String, Object> map){
		
		Department depart = (Department) map.get("depart");
		
		Employee emp = CommonUtils.toBean(map,Employee.class);
		
		emp.setDepartment(depart);
		
		Integer i =employeeMapper.saveEntry(emp);
	
		return i;
	}
	@Override
	public List<Employee> getEmployeeByDid(String dId) {
		List<Employee> emp =employeeMapper.getEmployeeByDid(dId);
		return emp;
	}
	@Override
	public List<Employee> getAllEmployee() {
		
		
		return null;
	}
}
