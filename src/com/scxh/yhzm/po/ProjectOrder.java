package com.scxh.yhzm.po;

import java.sql.Date;

//工程订单实体
public class ProjectOrder{
	private String orderId;//订单id
	private String projectName;//工程名称
	private String projectType;//工程类别
	private String projectAddr;//工程地址
	private String projectImg;//工程相关的图片
	private Double projectPrice;//工程定价
	private Date startDate;//工程开始时间
	private Date endDate;//工程结束时间
	private String projectDetail;//工程详细
	private String customerUnit;//客户单位
	private Employee employee;//外键
	
	public String getCustomerUnit() {
		return customerUnit;
	}
	public void setCustomerUnit(String customerUnit) {
		this.customerUnit = customerUnit;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getProjectAddr() {
		return projectAddr;
	}
	public void setProjectAddr(String projectAddr) {
		this.projectAddr = projectAddr;
	}
	public String getProjectImg() {
		return projectImg;
	}
	public void setProjectImg(String projectImg) {
		this.projectImg = projectImg;
	}
	public Double getProjectPrice() {
		return projectPrice;
	}
	public void setProjectPrice(Double projectPrice) {
		this.projectPrice = projectPrice;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getProjectDetail() {
		return projectDetail;
	}
	public void setProjectDetail(String projectDetail) {
		this.projectDetail = projectDetail;
	}
	
}