package com.scxh.yhzm.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.scxh.yhzm.dao.mapper.ProduceMapper;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.Produce;
import com.scxh.yhzm.service.ProduceService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.SaveFileUtil;

public class ProduceServiceImpl extends BaseServiceImpl<Produce> implements
		ProduceService<Produce> {
	
	private ProduceMapper<Produce> produceMapper;

	public void setProduceMapper(ProduceMapper<Produce> produceMapper) {
		
		this.produceMapper = produceMapper;
	}

	@Override
	public Integer addEntry(Map<String, Object> map) {

		Integer backNum = null;
		String fileName = SaveFileUtil.saveFile(map, SAVEDIRECTORY);
		map.put("proImages", fileName);
		Produce produce = CommonUtils.toBean(map, Produce.class);
		backNum = produceMapper.saveEntry(produce);
		return backNum;
	}

	@Override
	public PagingBean<Produce> getAllEntryFY(Map<String, Object> map) {

		Integer curPage = (Integer) map.get("curPage");
		Integer pageSize = (Integer) map.get("pageSize");
		Integer totalRecord = produceMapper.selectCountEntry(null);

		Integer startIndex = (curPage - 1) * pageSize;
		map.put("startIndex", startIndex);// select * from tab_name limit
											// startIndex,pageSize;

		List<Produce> produceList = produceMapper.getALLEntryFY(map);

		for (Produce p : produceList) {
			p.setProImages(SAVEDIRECTORY + p.getProImages());
		}

		PagingBean<Produce> pagingBean = new PagingBean<Produce>(totalRecord,
				curPage, pageSize, produceList);
		return pagingBean;
	}

	@Override
	public Produce getEntryById(Serializable id) {
		Produce produce = produceMapper.getEntryById(id);
		produce.setProImages(SAVEDIRECTORY + produce.getProImages());
		return produce;
	}

	@Override
	public Integer modEntry(Map<String, Object> map) {
		String flag = (String) map.get("flag");
		Integer backNum = null;
		
		if ("1".equals(flag)) {
			delImage(map);//删除旧的图片
			String fileName = SaveFileUtil.saveFile(map, SAVEDIRECTORY);
			map.put("proImages", fileName);
			map.remove(flag);
		}
		
		Produce produce = CommonUtils.toBean(map, Produce.class);
		backNum = produceMapper.updateEntry(produce);

		return backNum;
	}

	@Override
	public Integer deleteEntry(Map<String, Object> map) {
		String[] proIds = splitIds(map);
		delImage(map);
		return produceMapper.deleteEntry(proIds);
	}

	//抽取的删除图片的复用方法
	public void delImage(Map<String, Object> map) {
		
		String basePath = (String) map.get("basePath");
		String[] proIds = splitIds(map);
		
		List<String> imgAddrs = produceMapper.selectImgList(proIds);
		
		for (String oldImgAddr : imgAddrs) {
			
			File fp = new File(basePath + SAVEDIRECTORY + oldImgAddr);
			if (fp.exists() && fp.isFile()) {
				fp.delete();
			}
		}
	}
	
//拆分id为字符数组
	public String[] splitIds(Map<String, Object> map) {
		
		String proId = (String) map.get("proId");
		String[] proIds = null;
		if (null != proId && proId.contains(",")) {
			proIds = proId.split(",");
		} else {
			proIds = new String[] { proId };
		}
		return proIds;
	}
}
