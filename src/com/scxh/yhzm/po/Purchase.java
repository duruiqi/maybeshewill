package com.scxh.yhzm.po;

import java.sql.Date;

//采购实体
public class Purchase {
	private String purId;//
	private String materialName;//材料名称
	private String materialType;//材料材质
	private String materialImages;//材料图片
	private Integer purCount;//采购数量
	private Double purPrice;//采购金额
	private Boolean isPay;//是否付款
	private String payMethod;//付款类别
	private Date purDate;//采购时间
	
	private Department department;//外键
	
	
	public String getPurId() {
		return purId;
	}
	public void setPurId(String purId) {
		this.purId = purId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getMaterialImages() {
		return materialImages;
	}
	public void setMaterialImages(String materialImages) {
		this.materialImages = materialImages;
	}
	public Integer getPurCount() {
		return purCount;
	}
	public void setPurCount(Integer purCount) {
		this.purCount = purCount;
	}
	public Double getPurPrice() {
		return purPrice;
	}
	public void setPurPrice(Double purPrice) {
		this.purPrice = purPrice;
	}
	public Boolean getIsPay() {
		return isPay;
	}
	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public Date getPurDate() {
		return purDate;
	}
	public void setPurDate(Date purDate) {
		this.purDate = purDate;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

}
