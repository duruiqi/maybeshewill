package com.scxh.yhzm.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConversionException;

import com.scxh.yhzm.dao.mapper.EmployeeMapper;
import com.scxh.yhzm.dao.mapper.ProjectOrderMapper;
import com.scxh.yhzm.po.Employee;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.ProjectOrder;
import com.scxh.yhzm.service.ProjectOrderService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.SaveFileUtil;

public class ProjectOrderServiceImpl  extends BaseServiceImpl<ProjectOrder> implements ProjectOrderService<ProjectOrder>{
	private ProjectOrderMapper<ProjectOrder> projectOrderMapper;
	private EmployeeMapper<Employee> employeeMapper;

	@Override
	public Integer addEntry(Map<String,Object> map) {
		String ename = (String) map.get("ename");
		Integer backNum = null;
		Employee employee = (Employee) employeeMapper.getEntryByName(ename);
		if(employee != null){
			map.put("employee", employee);
			String fileName = SaveFileUtil.saveFile(map,SAVEDIRECTORY);
			map.put("projectImg", fileName);
			try {
				ProjectOrder po = CommonUtils.toBean(map,ProjectOrder.class);
				backNum = projectOrderMapper.saveEntry(po);
			} catch (Exception e) {
				e.printStackTrace();
				if(e instanceof ConversionException){
					throw new RuntimeException("不能转换的日期格式");
				}
			}
		}else{
			throw new RuntimeException("负责人不存在！");
		}
		
		return backNum;
	}
	@Override
	public PagingBean<ProjectOrder> getAllEntryFY(Map<String, Object> map) {
		
		Integer curPage = (Integer) map.get("curPage");
		Integer pageSize = (Integer) map.get("pageSize");
		Integer totalRecord = projectOrderMapper.selectCountEntry(null);
		
		Integer startIndex = (curPage-1)*pageSize;
		map.put("startIndex", startIndex);//select * from tab_name limit startIndex,pageSize;
		List<ProjectOrder> pOrderList= projectOrderMapper.getALLEntryFY(map);
		
		for(ProjectOrder po : pOrderList){
			
			po.setProjectImg(SAVEDIRECTORY + po.getProjectImg());
			
			Employee employee = employeeMapper.getEntryById(po.getEmployee().getEid());
			if(null != employee){
				po.setEmployee(employee);
			}
		}
		
		PagingBean<ProjectOrder> pagingBean = new PagingBean<ProjectOrder>(totalRecord,curPage,pageSize,pOrderList);
		return pagingBean;
	}
	
	@Override
	public ProjectOrder getEntryById(Serializable id) {
		ProjectOrder pOrder = projectOrderMapper.getEntryById(id);
		pOrder.setProjectImg(SAVEDIRECTORY + pOrder.getProjectImg());
		pOrder.setEmployee(employeeMapper.getEntryById(pOrder.getEmployee().getEid()));
		return pOrder;
	}


	@Override
	public Integer modEntry(Map<String, Object> map) {
		
		String ename = (String) map.get("ename");
		String flag = (String)map.get("flag");
		Integer backNum = null;
		Employee employee = (Employee) employeeMapper.getEntryByName(ename);
		if(employee != null){
			map.put("employee", employee);
			if("1".equals(flag)){
				String fileName = SaveFileUtil.saveFile(map,SAVEDIRECTORY);
				map.put("projectImg", fileName);
				map.remove(flag);
			}
			try {
				ProjectOrder po = CommonUtils.toBean(map,ProjectOrder.class);
				backNum = projectOrderMapper.updateEntry(po);
			} catch (Exception e) {
				e.printStackTrace();
				if(e instanceof ConversionException){
					throw new RuntimeException("不能转换的日期格式");
				}
			}
		}else{
			throw new RuntimeException("负责人不存在！");
		}
		
		return backNum;
	}


	@Override
	public Integer deleteEntry(Map<String,Object> map) {
		String basePath = (String)map.get("basePath");
		String orderId = (String)map.get("orderId");
		
		String[] orderIds = null;
		if(orderId.contains(",")){
			orderIds = orderId.split(",");
		}else{
			orderIds = new String[]{orderId};
		}
		List<String> imgAddrs = projectOrderMapper.selectImgList(orderIds);
		
		for(String oldImgAddr : imgAddrs){
			File fp=new File(basePath + SAVEDIRECTORY + oldImgAddr);
			if(fp.exists()&&fp.isFile()){
				fp.delete();
			}
		}
		
		return projectOrderMapper.deleteEntry(orderIds);
	}

	public void setProjectOrderMapper(ProjectOrderMapper<ProjectOrder> projectOrderMapper) {
		this.projectOrderMapper = projectOrderMapper;
	}

	public void setEmployeeMapper(EmployeeMapper<Employee> employeeMapper) {
		this.employeeMapper = employeeMapper;
	}
}
