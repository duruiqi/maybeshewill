package com.scxh.yhzm.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.SalaryMapper;
import com.scxh.yhzm.po.Employee;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.Produce;
import com.scxh.yhzm.po.Salary;
import com.scxh.yhzm.service.SalaryService;
import com.scxh.yhzm.util.CommonUtils;

/**
 * 
 * @author HPH
 * 
 */
public class SalaryServiceImpl extends BaseServiceImpl<Salary> implements
		SalaryService<Salary> {

	private SalaryMapper salaryMapper;

	public SalaryMapper getSalaryMapper() {
		return salaryMapper;
	}

	public void setSalaryMapper(SalaryMapper salaryMapper) {
		this.salaryMapper = salaryMapper;
	}

	public Map<String, Object> getAllEntry(Map<String, Object> map) {

		return null;
		
	}

	public List<Salary> getAllEntry2(Map<String, Object> map) {
		
		return null;

	}

	public PagingBean<Salary> getAllEntryFY(Map<String, Object> map) {
		
		Integer curPage = (Integer) map.get("curPage");
		Integer pageSize = (Integer) map.get("pageSize");
		
		//		Integer totalRecord = EmployeeMapper.selectCountEntry();//原来的结果
		Integer totalRecord = salaryMapper.selectCountEntry(null);
		
		Integer startIndex = (curPage-1)*pageSize;
		
		map.put("startIndex", startIndex);//select * from tab_name limit startIndex,pageSize;
		
		List<Salary> salaryList= salaryMapper.getALLEntryFY(map);
		
		PagingBean<Salary> pagingBean = new PagingBean<Salary>(totalRecord,curPage,pageSize,salaryList);
		
		return  pagingBean;

	}

	public Salary getEntryById(Serializable id) {
		
		return salaryMapper.getEntryById(id);

	}

	public Salary getEntryByName(Serializable name) {
		return null;

	}

	public Integer addEntry(Map<String, Object> map) {
		
		
		return null;

	}

	public Integer addEntry(Salary salary) {
		
		return null;

	}

	public Integer modEntry(Map<String, Object> map) {
		
		Employee emps =  (Employee) map.get("emp");
		
		Salary sal = CommonUtils.toBean(map, Salary.class);
		
		sal.setEmployee(emps);
		
		return salaryMapper.updateEntry(sal);

	}

	public Integer modEntry(Salary salary) {
		return null;

	}

	public Integer deleteEntry(Map<String, Object> map) {
		return null;

	}

	public Integer deleteEntry(Serializable id) {
		return null;

	}

}
