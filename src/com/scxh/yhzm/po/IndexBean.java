package com.scxh.yhzm.po;

import java.util.List;


public class IndexBean {
	private List<MainBanner> bannerList;
	private FirmInfo firmInfo;
	private List<News> newsList;
	private List<ProjectOrder> projectList;
	public IndexBean(){}
	
	public IndexBean(List<MainBanner> bannerList, FirmInfo firmInfo,
			List<News> newsList, List<ProjectOrder> projectList) {
		this.bannerList = bannerList;
		this.firmInfo = firmInfo;
		this.newsList = newsList;
		this.projectList = projectList;
	}

	public List<MainBanner> getBannerList() {
		return bannerList;
	}
	public void setBannerList(List<MainBanner> bannerList) {
		this.bannerList = bannerList;
	}
	public FirmInfo getFirmInfo() {
		return firmInfo;
	}
	public void setFirmInfo(FirmInfo firmInfo) {
		this.firmInfo = firmInfo;
	}
	public List<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	public List<ProjectOrder> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<ProjectOrder> projectList) {
		this.projectList = projectList;
	}
	
}
