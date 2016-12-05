package com.scxh.yhzm.service;

import java.util.List;

import com.scxh.yhzm.po.Employee;

public interface EmployeeService<T> extends BaseService<T>{
	List<T> getSsEmployee(String[] params);

	List<Employee> getEmployeeByDid(String i);
	
	List<Employee> getAllEmployee();
}
