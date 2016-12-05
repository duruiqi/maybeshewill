package com.scxh.yhzm.po;
//管理员实体
public class Admin {
	private String adminId;
	private String adminName;
	private String adminPasswd;
	private Boolean state;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPasswd() {
		return adminPasswd;
	}
	public void setAdminPasswd(String adminPasswd) {
		this.adminPasswd = adminPasswd;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
}
