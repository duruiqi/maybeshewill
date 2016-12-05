package com.scxh.yhzm.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.NewsMapper;
import com.scxh.yhzm.po.News;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.service.NewsService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.SaveFileUtil;

public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService<News> {
	
	private NewsMapper<News> newsMapper;

	public void setNewsMapper(NewsMapper<News> newsMapper) {
		this.newsMapper = newsMapper;
	}

	@Override
	public Integer addEntry(Map<String, Object> map) {

		Integer backNum = null;
		String fileName = SaveFileUtil.saveFile(map, SAVEDIRECTORY);
		map.put("nImages", fileName);
		map.put("nid", CommonUtils.uuid());
		News news = CommonUtils.toBean(map, News.class);
		backNum = newsMapper.saveEntry(news);
		return backNum;
	}

	@Override
	public PagingBean<News> getAllEntryFY(Map<String, Object> map) {

		Integer curPage = (Integer) map.get("curPage");
		Integer pageSize = (Integer) map.get("pageSize");
		Integer totalRecord = newsMapper.selectCountEntry(null);

		Integer startIndex = (curPage - 1) * pageSize;
		map.put("startIndex", startIndex);// select * from tab_name limit
											// startIndex,pageSize;

		List<News> newsList = newsMapper.getALLEntryFY(map);

		for (News news : newsList) {
			news.setnImages(SAVEDIRECTORY + news.getnImages());
		}

		PagingBean<News> pagingBean = new PagingBean<News>(totalRecord,curPage, pageSize, newsList);
		return pagingBean;
	}

	@Override
	public News getEntryById(Serializable id) {
		News news = newsMapper.getEntryById(id);
		news.setnImages(SAVEDIRECTORY + news.getnImages());
		return news;
	}

	@Override
	public Integer modEntry(Map<String, Object> map) {
		String flag = (String) map.get("flag");
		Integer backNum = null;
		
		if ("1".equals(flag)) {
			delImage(map);//删除旧的图片
			String fileName = SaveFileUtil.saveFile(map, SAVEDIRECTORY);
			map.put("nImages", fileName);
			map.remove(flag);
		}
		
		News news = CommonUtils.toBean(map, News.class);
		backNum = newsMapper.updateEntry(news);

		return backNum;
	}

	@Override
	public Integer deleteEntry(Map<String, Object> map) {
		String[] nids = splitIds(map);
		delImage(map);
		return newsMapper.deleteEntry(nids);
	}

	//抽取的删除图片的复用方法
	public void delImage(Map<String, Object> map) {
		String basePath = (String) map.get("basePath");
		String[] strs = splitIds(map);
		
		List<String> imgAddrs = newsMapper.selectImgList(strs);
		
		for (String oldImgAddr : imgAddrs) {
			
			File fp = new File(basePath + SAVEDIRECTORY + oldImgAddr);
			if (fp.exists() && fp.isFile()) {
				fp.delete();
			}
		}
	}
	
//拆分id为字符数组
	public String[] splitIds(Map<String, Object> map) {
		
		String nid = (String) map.get("nid");
		String[] nids = null;
		if (null != nid && nid.contains(",")) {
			nids = nid.split(",");
		} else {
			nids = new String[] { nid };
		}
		return nids;
	}
}
