package com.scxh.yhzm.po;
//联系我们实体

public class ContactUs {
	private Integer usId;
	private String linkman;//联系人
	private String email;//邮箱
	private String qq;
	private String fax;//传真
	private String postcode;//邮编
	private String phone;//联系电话
	private String linkAddr;//联系地址
	private FirmInfo firmInfo;
	
	public FirmInfo getFirmInfo() {
		return firmInfo;
	}
	public void setFirmInfo(FirmInfo firmInfo) {
		this.firmInfo = firmInfo;
	}
	public Integer getUsId() {
		return usId;
	}
	public void setUsId(Integer usId) {
		this.usId = usId;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLinkAddr() {
		return linkAddr;
	}
	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

}
