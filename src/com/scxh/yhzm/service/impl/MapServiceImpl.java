package com.scxh.yhzm.service.impl;

import java.io.Serializable;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.MapMapper;
import com.scxh.yhzm.po.OnlineMap;
import com.scxh.yhzm.service.MapService;
import com.scxh.yhzm.util.CommonUtils;

public class MapServiceImpl  extends BaseServiceImpl<OnlineMap> implements MapService<OnlineMap>{
	private MapMapper<OnlineMap> mapMapper;

	public void setMapMapper(MapMapper<OnlineMap> mapMapper) {
		this.mapMapper = mapMapper;
	}

	@Override
	public OnlineMap getEntryById(Serializable id){
		return mapMapper.getEntryById(id);
	}


	@Override
	public Integer modEntry(Map<String, Object> map) {
		return mapMapper.updateEntry(CommonUtils.toBean(map, OnlineMap.class));
	}

}