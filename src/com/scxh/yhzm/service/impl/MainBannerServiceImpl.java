package com.scxh.yhzm.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.MainBannerMapper;
import com.scxh.yhzm.po.MainBanner;
import com.scxh.yhzm.service.MainBannerService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.SaveFileUtil;

public class MainBannerServiceImpl  extends BaseServiceImpl<MainBanner> implements MainBannerService<MainBanner>{
	private MainBannerMapper<MainBanner> mainBannerMapper;
	
	public void setMainBannerMapper(MainBannerMapper<MainBanner> mainBannerMapper) {
		this.mainBannerMapper = mainBannerMapper;
	}


	@Override
	public Integer addEntry(Map<String,Object> map) {
		String fileName = SaveFileUtil.saveFile(map,SAVEDIRECTORY);
		map.put("bannerImg",fileName);
		MainBanner mainBanner = CommonUtils.toBean(map,MainBanner.class);
		
		mainBannerMapper.updateEntry(new MainBanner(mainBanner.getTimeslice()));
		
		return mainBannerMapper.saveEntry(mainBanner);
	}
	
	@Override
	public List<MainBanner> getAllEntry2(Map<String, Object> map) {
		List<MainBanner> mainBannerList= mainBannerMapper.getAllEntry(CommonUtils.toBean(map, MainBanner.class));
		for(MainBanner logo:mainBannerList){
			logo.setBannerImg(SAVEDIRECTORY + logo.getBannerImg()); 
		}
		return mainBannerList;
	}
	
	@Override
	public MainBanner getEntryById(Serializable id){
		return mainBannerMapper.getEntryById(id);
	}
	
	@Override
	public Integer modEntry(Map<String, Object> map) {
		String flag = (String) map.get("flag");
		Integer backNum = null;
		
		if ("1".equals(flag)) {
			delImage(map);//删除旧的图片
			String fileName = SaveFileUtil.saveFile(map, SAVEDIRECTORY);
			map.put("bannerImg", fileName);
			map.remove(flag);
		}
		
		MainBanner mainBanner = CommonUtils.toBean(map, MainBanner.class);
		mainBannerMapper.updateEntry(new MainBanner(mainBanner.getTimeslice()));
		
		backNum = mainBannerMapper.updateEntry(mainBanner);

		return backNum;
	}

	@Override
	public Integer deleteEntry(Map<String, Object> map) {
		String[] nids = splitIds(map);
		delImage(map);
		return mainBannerMapper.deleteEntry(nids);
	}

	//抽取的删除图片的复用方法
	public void delImage(Map<String, Object> map) {
		String basePath = (String) map.get("basePath");
		String[] strs = splitIds(map);
		
		List<String> imgAddrs = mainBannerMapper.selectImgList(strs);
		
		for (String oldImgAddr : imgAddrs) {
			
			File fp = new File(basePath + SAVEDIRECTORY + oldImgAddr);
			if (fp.exists() && fp.isFile()) {
				fp.delete();
			}
		}
	}
	
//拆分id为字符数组
	public String[] splitIds(Map<String, Object> map) {
		
		String nid = (String) map.get("bId");
		String[] nids = null;
		if (null != nid && nid.contains(",")) {
			nids = nid.split(",");
		} else {
			nids = new String[] { nid };
		}
		return nids;
	}

}