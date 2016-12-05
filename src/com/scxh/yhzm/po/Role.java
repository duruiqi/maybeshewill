package com.scxh.yhzm.po;

//角色实体
public class Role {
	private String roleId;
	private String roleType;//角色的类别
	private String accreditState;//授权状态属性：一个int有32位,用后4位表示crud操作，位取值0或1
	private String accreditCode;//授权掩码属性：用一个int来表示授权的继承状态
	private Admin admin;//外键
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getAccreditState() {
		return accreditState;
	}
	public void setAccreditState(String accreditState) {
		this.accreditState = accreditState;
	}
	public String getAccreditCode() {
		return accreditCode;
	}
	public void setAccreditCode(String accreditCode) {
		this.accreditCode = accreditCode;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
}
