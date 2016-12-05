package com.scxh.yhzm.po;

//会员实体
public class User {
	private String userId;//
	private String userName;//用户名
	private String userPasswd;//密码
	private String stateCode;//激活码
	private String email;//激活邮箱
	private Boolean state;//状态
	private Boolean delMark;//删除标志
	private String remark;//备注
	private Boolean loginState;//登录状态
	public Boolean getLoginState() {
		return loginState;
	}
	public void setLoginState(Boolean loginState) {
		this.loginState = loginState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public Boolean getDelMark() {
		return delMark;
	}
	public void setDelMark(Boolean delMark) {
		this.delMark = delMark;
	}
	
}
