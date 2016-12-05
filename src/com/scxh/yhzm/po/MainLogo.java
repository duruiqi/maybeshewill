package com.scxh.yhzm.po;

public class MainLogo {
	
	private Integer mainId;
	private String mlogo;//公司logo
	private Boolean state;//显示状态
	
	public MainLogo(){}
	public MainLogo(Boolean state){this.state = state;}
	
	public Integer getMainId() {
		return mainId;
	}
	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}
	
	public String getMlogo() {
		return mlogo;
	}
	public void setMlogo(String mlogo) {
		this.mlogo = mlogo;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	
}
