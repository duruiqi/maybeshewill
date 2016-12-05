package com.scxh.yhzm.po;

import java.sql.Date;
//新闻动态实体
public class News {
	private String nid;
	private String nTitle;//新闻标题
	private String nImages;//新闻图片
	private String nContent;//新闻内容
	private Date nDate;//新闻发布的日期
	private Boolean state;//是否显示在前台
	private String customerUnit;//客户单位
	
	public String getCustomerUnit() {
		return customerUnit;
	}
	public void setCustomerUnit(String customerUnit) {
		this.customerUnit = customerUnit;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getnImages() {
		return nImages;
	}
	public void setnImages(String nImages) {
		this.nImages = nImages;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public Date getnDate() {
		return nDate;
	}
	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
}
