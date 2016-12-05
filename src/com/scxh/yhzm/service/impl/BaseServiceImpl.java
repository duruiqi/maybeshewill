package com.scxh.yhzm.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T>{
	public static final String SAVEDIRECTORY = "/upload/images/";
	@Override
	public Map<String, Object> getAllEntry(Map<String, Object> map) {
		return null;
	}

	@Override
	public PagingBean<T> getAllEntryFY(Map<String, Object> map) {
		return null;
	}

	@Override
	public T getEntryById(Serializable id) {
		return null;
	}
	
	@Override
	public Integer addEntry(Map<String, Object> map) {
		return null;
	}

	@Override
	public Integer modEntry(Map<String, Object> map) {
		return null;
	}

	@Override
	public Integer deleteEntry(Map<String, Object> map) {
		return null;
	}

	@Override
	public T getEntryByCoulmn(T t) {
		return null;
	}

	@Override
	public T getEntryByName(Serializable name) {
		return null;
	}

	@Override
	public Integer addEntry(T t) {
		return null;
	}

	@Override
	public Integer modEntry(T t) {
		return null;
	}

	@Override
	public Integer deleteEntry(T t) {
		return null;
	}

	@Override
	public List<T> getAllEntry2(Map<String, Object> map) {
		return null;
	}

	@Override
	public T getEntryByCoulmn(Serializable name) {
		// TODO Auto-generated method stub
		return null;
	}

}
