package com.scxh.yhzm.service.impl;

import java.util.List;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.DepartmentMapper;
import com.scxh.yhzm.po.Department;
import com.scxh.yhzm.service.DepartmentService;


/**
 * department实现类
 * @author HPH
 *
 */
public class DepartmentServiceImpl implements DepartmentService {
//	private DepartmentMapper DepartmentrDao;
	private DepartmentMapper departmentMapper;

//	public DepartmentMapper getDepartmentDao() {
//		return DepartmentrDao;
//	}
//
//	public void setDepartmentDao(DepartmentMapper DepartmentDao) {
//		this.DepartmentrDao = DepartmentDao;
//	}

	public DepartmentMapper getDepartmentMapper() {
		return departmentMapper;
	}

	public void setDepartmentMapper(DepartmentMapper DepartmentMapper) {
		this.departmentMapper = DepartmentMapper;
	}
	
	
	
	@Override
	public Integer addDepartment(Department department) {
		Integer i = departmentMapper.addDepartment(department);
		return i;
	}

	@Override
	public Integer delDepartment(String departmentIds) {
		return departmentMapper.delById(departmentIds);
	}

	@Override
	public Integer modDepartment(Department department) {
		Integer i =departmentMapper.updateDepartment(department);
		return i;
	}

	@Override
	public Department getDepartment(String departmentId) {
		Department department = departmentMapper.getDepartmentById(departmentId);
		return department;
	}

	@Override
	public List<Department> getAllDepartment() {
		List<Department> departmentsList= departmentMapper.listAll();
		return departmentsList;
	}


}