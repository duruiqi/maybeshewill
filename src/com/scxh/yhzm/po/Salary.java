package com.scxh.yhzm.po;
//薪资实体
public class Salary {
	private Integer sid;//
	private Double sBase;//基本薪资
	private Double sCount;//计件提成
	private Double sBusiness;//业务提成
	private Employee employee;//外键
//	private Department department;//外键 之所以注释是因为发现丫数据库压根没这外键。
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Double getsBase() {
		return sBase;
	}
	public void setsBase(Double sBase) {
		this.sBase = sBase;
	}
	public Double getsCount() {
		return sCount;
	}
	public void setsCount(Double sCount) {
		this.sCount = sCount;
	}
	public Double getsBusiness() {
		return sBusiness;
	}
	public void setsBusiness(Double sBusiness) {
		this.sBusiness = sBusiness;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
/*	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}*/
}
