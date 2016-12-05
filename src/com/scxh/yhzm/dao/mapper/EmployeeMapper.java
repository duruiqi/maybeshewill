package com.scxh.yhzm.dao.mapper;

import java.util.List;

import com.scxh.yhzm.po.Employee;

public interface EmployeeMapper<T> extends BaseMapper<T>{
	
	List<Employee> getSsEmployee(String[] params);

	List<Employee> getEmployeeByDid(String dId);
	
	List<Employee> getAllEmployee();
}
