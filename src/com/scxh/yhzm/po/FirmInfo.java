package com.scxh.yhzm.po;

import java.sql.Date;

//公司信息实体
public class FirmInfo {
	private String firmId;//
	private String firmName;//公司名字
	private Date founds;//成立时间
	private String loginFund;//注册资金
	private String introduce;//公司介绍
	private String imagesAddr;//公司图片地址
	private String firmAddr;//公司地址
	private String beiAnInfo;//网站备案信息
	private Boolean state;
	//用于展示数据属性数据表中没有对应的字段
	private String[] imgAddrs;
	
	public FirmInfo(){}
	
	public FirmInfo(Boolean state){
		this.state = state;
	}
	
	
	public String[] getImgAddrs() {
		return imgAddrs;
	}

	public void setImgAddrs(String[] imgAddrs) {
		this.imgAddrs = imgAddrs;
	}

	public Boolean getState() {
		
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public String getFirmId() {
		return firmId;
	}
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}



	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public Date getFounds() {
		return founds;
	}
	public void setFounds(Date founds) {
		this.founds = founds;
	}
	
	public String getLoginFund() {
		return loginFund;
	}
	public void setLoginFund(String loginFund) {
		this.loginFund = loginFund;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getImagesAddr() {
		return imagesAddr;
	}
	public void setImagesAddr(String imagesAddr) {
		this.imagesAddr = imagesAddr;
	}
	public String getFirmAddr() {
		return firmAddr;
	}
	public void setFirmAddr(String firmAddr) {
		this.firmAddr = firmAddr;
	}
	public String getBeiAnInfo() {
		return beiAnInfo;
	}
	public void setBeiAnInfo(String beiAnInfo) {
		this.beiAnInfo = beiAnInfo;
	}
	
	
}
