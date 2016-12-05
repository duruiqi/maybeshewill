package com.scxh.yhzm.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.MainLogoMapper;
import com.scxh.yhzm.po.MainLogo;
import com.scxh.yhzm.service.MainLogoService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.SaveFileUtil;

public class MainLogoServiceImpl  extends BaseServiceImpl<MainLogo> implements MainLogoService<MainLogo>{
	private MainLogoMapper<MainLogo> mainLogoMapper;
	
	public void setMainLogoMapper(MainLogoMapper<MainLogo> mainLogoMapper) {
		this.mainLogoMapper = mainLogoMapper;
	}


	@Override
	public Integer addEntry(Map<String,Object> map) {
		String fileName = SaveFileUtil.saveFile(map,SAVEDIRECTORY);
		map.put("mlogo",fileName);
		MainLogo mainLogo = CommonUtils.toBean(map,MainLogo.class);
		if(mainLogo.getState()){
			mainLogoMapper.updateEntry(new MainLogo(false));
		}
		return mainLogoMapper.saveEntry(mainLogo);
	}
	
	@Override
	public List<MainLogo> getAllEntry2(Map<String, Object> map) {
		List<MainLogo> mainLogoList= mainLogoMapper.getAllEntry(CommonUtils.toBean(map, MainLogo.class));
		for(MainLogo logo:mainLogoList){
			logo.setMlogo(SAVEDIRECTORY + logo.getMlogo()); 
		}
		return mainLogoList;
	}
	
	@Override
	public MainLogo getEntryById(Serializable id){
		return mainLogoMapper.getEntryById(id);
	}
	
	@Override
	public MainLogo getEntryByCoulmn(MainLogo t) {
		
		MainLogo logo = mainLogoMapper.getEntryByCoulmn(t);
		
		logo.setMlogo(SAVEDIRECTORY + logo.getMlogo()); 
		
		return logo;
	}

	@Override
	public Integer modEntry(Map<String, Object> map) {
		String flag = (String) map.get("flag");
		Integer backNum = null;
		
		if ("1".equals(flag)) {
			delImage(map);//删除旧的图片
			String fileName = SaveFileUtil.saveFile(map, SAVEDIRECTORY);
			map.put("mlogo", fileName);
			map.remove(flag);
		}
		
		MainLogo mainLogo = CommonUtils.toBean(map, MainLogo.class);
		if(mainLogo.getState()){
			mainLogoMapper.updateEntry(new MainLogo(false));
		}
		
		backNum = mainLogoMapper.updateEntry(mainLogo);

		return backNum;
	}

	@Override
	public Integer deleteEntry(Map<String, Object> map) {
		String[] nids = splitIds(map);
		delImage(map);
		return mainLogoMapper.deleteEntry(nids);
	}

	//抽取的删除图片的复用方法
	public void delImage(Map<String, Object> map) {
		String basePath = (String) map.get("basePath");
		String[] strs = splitIds(map);
		
		List<String> imgAddrs = mainLogoMapper.selectImgList(strs);
		
		for (String oldImgAddr : imgAddrs) {
			
			File fp = new File(basePath + SAVEDIRECTORY + oldImgAddr);
			if (fp.exists() && fp.isFile()) {
				fp.delete();
			}
		}
	}
	
//拆分id为字符数组
	public String[] splitIds(Map<String, Object> map) {
		
		String nid = (String) map.get("mainId");
		String[] nids = null;
		if (null != nid && nid.contains(",")) {
			nids = nid.split(",");
		} else {
			nids = new String[] { nid };
		}
		return nids;
	}

}