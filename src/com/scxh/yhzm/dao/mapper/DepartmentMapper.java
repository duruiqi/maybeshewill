package com.scxh.yhzm.dao.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.po.Department;

/**
 * 
 * @author HPH
 * 
 */
public interface DepartmentMapper<T> extends BaseMapper<T>{
	
	/* 增加一条department记录 */
	public Integer addDepartment(Department department);
	
	/* 根据部门ID删除部门记录 */
	Integer delById(Serializable id);

	/* 根据部门ID查询对应的部门记录 */
	Department getDepartmentById(String id);

	/* 查询所有部门记录并存储在list集合中 */
	List<Department> listAll();

	/* 根据给出的department对象更新department表中的记录 */
	Integer updateDepartment(Department department);

	/* 查询所有的department记录 */
	public List<Department> selectDepartment(Integer departmentId);
}
