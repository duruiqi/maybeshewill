package com.scxh.yhzm.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.po.Department;

/**
 * department接口
	private Integer did;
	private String dname;//部门名称
	private String principal;//负责人
	private Date FOUND; 建立时间
 * @author HPH
 *
 */
public interface DepartmentService{
	
	
	/**
	 * 增加一个部门,返回主键
	 * @param Department
	 * @return
	 */
	public Integer addDepartment(Department department);
	
	/**
	 * 删除部门,返回影响行数
	 * @param Department
	 * @return
	 */
	public Integer delDepartment(String departmentIds);
	
	/**
	 * 修改部门,返回影响行数
	 * @param Department
	 * @return
	 */
	public Integer modDepartment(Department department);
	
	/**
	 * 根据ID获取单个部门
	 * @param DepartmentId
	 * @return
	 */
	public Department getDepartment(String departmentId);
	
	/**
	 * 获取所有的部门记录
	 * @param DepartmentId
	 * @return
	 */
	public List<Department> getAllDepartment();
	
	
}
