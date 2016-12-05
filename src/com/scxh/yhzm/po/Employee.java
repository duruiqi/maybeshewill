package com.scxh.yhzm.po;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
//员工实体
public class Employee {
	private Integer eid;
	private String ename;//员工名字
	private String mgr;//员工的上级领导
	private Date hiredate;//进公司的时间
	private String gender;//性别
	private Date birthday;//生日
	private String phone;//电话号码
	private String qq;
	private String email;
	private String education;
	private String job;//从事的工作
	private String did;//部门编号
	private Department department;//所属部门
	private List<ProjectOrder> projectOrder = new ArrayList<ProjectOrder>();
	
	
	public List<ProjectOrder> getProjectOrder() {
		return projectOrder;
	}
	public void setProjectOrder(List<ProjectOrder> projectOrder) {
		this.projectOrder = projectOrder;
	}
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getMgr() {
		return mgr;
	}
	public void setMgr(String mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}
