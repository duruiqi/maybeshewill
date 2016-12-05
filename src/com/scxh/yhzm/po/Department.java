package com.scxh.yhzm.po;

import java.sql.Date;

//部门实体
public class Department {
	private Integer dId;
	private String dname;//部门名称
	private String principal;//部门负责人
	private Date found;//成立时间

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Date getfound() {
		return found;
	}

	public void setfound(Date found) {
		this.found = found;
	}

	public Department(Integer dId, String dname, String principal, Date found) {
		this.dId = dId;
		this.dname = dname;
		this.principal = principal;
		this.found = found;
	}

	public Department(String dname, String principal, Date found) {
		this.dname = dname;
		this.principal = principal;
		this.found = found;
	}

	public Department() {

	}
}
