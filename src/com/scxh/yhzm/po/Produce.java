package com.scxh.yhzm.po;

//产品实体
public class Produce {
	private Integer proId;
	private String proName;//灯具名称
	private String proType;//灯具型号
	private String proImages;//灯具图片
	private String proCatogroy;//灯具类别
	private String proMaterial;//灯具材质
	private String proArea;//产品的产地
	private String proSummary;//产品的摘要
	private String proDesc;//产品的详细描述
	private String proParameter;//产品的参数
	private Boolean state;//是否显示
	
	
	
	public String getProArea() {
		return proArea;
	}
	public void setProArea(String proArea) {
		this.proArea = proArea;
	}
	public String getProSummary() {
		return proSummary;
	}
	public void setProSummary(String proSummary) {
		this.proSummary = proSummary;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getProParameter() {
		return proParameter;
	}
	public void setProParameter(String proParameter) {
		this.proParameter = proParameter;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public String getProImages() {
		return proImages;
	}
	public void setProImages(String proImages) {
		this.proImages = proImages;
	}
	public String getProCatogroy() {
		return proCatogroy;
	}
	public void setProCatogroy(String proCatogroy) {
		this.proCatogroy = proCatogroy;
	}
	public String getProMaterial() {
		return proMaterial;
	}
	public void setProMaterial(String proMaterial) {
		this.proMaterial = proMaterial;
	}
	
}
