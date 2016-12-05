package com.scxh.yhzm.dao.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseMapper<T> {
	
	public List<T> getAllEntry(T t);
	
	public List<T> getAllEntry2();
	
	public List<T> getALLEntryFY(Map<String,Object> paramMap);
	
	public T getEntryById(Serializable id);
	
	public T getEntryByName(Serializable name);
	
	public T getEntryByCoulmn(T t);
	
	public Integer saveEntry(T t);
	
	public Integer updateEntry(T t);
	
	public Integer deleteEntry(Serializable id);
	
	public Integer selectCountEntry(Map<String,Object> paramMap);
	
	public List<String> selectImgList(String[] strs);
}
