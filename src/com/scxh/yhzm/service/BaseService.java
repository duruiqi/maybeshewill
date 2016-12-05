package com.scxh.yhzm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.po.PagingBean;


public interface BaseService<T>{
	
	public Map<String, Object> getAllEntry(Map<String,Object> map);
	
	public List<T> getAllEntry2(Map<String,Object> map);
	
	public PagingBean<T> getAllEntryFY(Map<String,Object> map);
	
	public T getEntryById(Serializable id);
	
	public T getEntryByName(Serializable name);
	
	public T getEntryByCoulmn(T t);
	
	public T getEntryByCoulmn(Serializable name);
	
	public Integer addEntry(Map<String,Object> map);
	
	public Integer addEntry(T t);
	
	public Integer modEntry(Map<String,Object> map);
	
	public Integer modEntry(T t);
	
	public Integer deleteEntry(Map<String,Object> map);
	
	public Integer deleteEntry(T t);
	
}
