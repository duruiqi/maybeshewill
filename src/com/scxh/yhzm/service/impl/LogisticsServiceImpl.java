package com.scxh.yhzm.service.impl;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConversionException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.dao.mapper.DepartmentMapper;
import com.scxh.yhzm.dao.mapper.LogisticsMapper;
import com.scxh.yhzm.po.Department;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.Purchase;
import com.scxh.yhzm.service.LogisticsService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.ExcelUtil;

public class LogisticsServiceImpl  extends BaseServiceImpl<Purchase> implements LogisticsService<Purchase>{
	
	private LogisticsMapper<Purchase> logisticsMapper;
	
	private DepartmentMapper<Department> departmentMapper;
	
	public void setLogisticsMapper(LogisticsMapper<Purchase> logisticsMapper) {
		this.logisticsMapper = logisticsMapper;
	}
	
	public void setDepartmentMapper(DepartmentMapper<Department> departmentMapper) {
		this.departmentMapper = departmentMapper;
	}
	
	@Override
	public Integer addEntry(Map<String,Object> map) {
		Purchase purchase = CommonUtils.toBean(map, Purchase.class);
		setdept(map, purchase);
		return logisticsMapper.saveEntry(purchase);
	}
	
	
	@Override
	public PagingBean<Purchase> getAllEntryFY(Map<String, Object> map) {
		
		Integer curPage = (Integer) map.get("curPage");
		Integer pageSize = (Integer) map.get("pageSize");
		
		Integer totalRecord = logisticsMapper.selectCountEntry(null);
		
		Integer startIndex = (curPage-1)*pageSize;
		
		map.put("startIndex", startIndex);//select * from tab_name limit startIndex,pageSize;
		List<Purchase> purchaseList= logisticsMapper.getALLEntryFY(map);
		
		PagingBean<Purchase> pagingBean = new PagingBean<Purchase>(totalRecord,curPage,pageSize,purchaseList);
		return pagingBean;
	}

	
	@Override
	public Purchase getEntryById(Serializable id){
		
		Purchase purchase = logisticsMapper.getEntryById(id);
		return purchase;
	}



	@Override
	public Purchase getEntryByCoulmn(Purchase purchase) {
		return logisticsMapper.getEntryByCoulmn(purchase);
	}

	@Override
	public Integer modEntry(Map<String, Object> map) {
		Integer backNum = null;
		try {
			Purchase purchase = CommonUtils.toBean(map, Purchase.class);
			setdept(map, purchase);
			backNum = logisticsMapper.updateEntry(purchase);
			} catch (Exception e) {
				if(e instanceof ConversionException){
					throw new RuntimeException("不能转换的日期格式");
				}else{
					throw new RuntimeException(e);
				}
			}
		
		return backNum;
	}

	@Override
	public Integer deleteEntry(Map<String,Object> map) {
		String purId = (String)map.get("purId");
		String[] purIds = splitStr(purId);
		
		return logisticsMapper.deleteEntry(purIds);
	}

	
	
	@Override
	public List<Purchase> getAllEntry2(Map<String, Object> map) {
		List<Purchase> purchaseList = logisticsMapper.getALLEntryFY(map);
		return purchaseList;
	}

	@Override
	public void importExcel(CommonsMultipartFile excleFile) {
		
		ExcelUtil.importExcel(excleFile,departmentMapper,logisticsMapper);
	}

	
	@Override
	public void exportExcel(List<Purchase> purchaseList,
			OutputStream outputStream) {
		ExcelUtil.exportUserExcel(purchaseList, outputStream);
	}

	
	public String[] splitStr(String str) {
		String[] strs = null;
		if(str.contains(",")){
			strs = str.split(",");
		}else{
			strs = new String[]{str};
		}
		return strs;
	}
	
	public void setdept(Map<String, Object> map, Purchase purchase) {
		String dname = (String)map.get("dname");
		Department dept = (Department) departmentMapper.getEntryByName(dname);
		purchase.setDepartment(dept);
	}
}