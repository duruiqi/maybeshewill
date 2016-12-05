package com.scxh.yhzm.po;

public class MainBanner {
	
	private Integer bId;
	private String bannerImg;///#幻灯片图片
	private Integer timeslice;//#设置间隔时间
	private Boolean state;//#设置显示状态
	public MainBanner(){}
	
	public MainBanner(Integer timeslice){
		this.timeslice = timeslice;
	}
	public Integer getbId() {
		return bId;
	}
	public void setbId(Integer bId) {
		this.bId = bId;
	}
	public String getBannerImg() {
		return bannerImg;
	}
	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public Integer getTimeslice() {
		return timeslice;
	}
	public void setTimeslice(Integer timeslice) {
		this.timeslice = timeslice;
	}
	
}
