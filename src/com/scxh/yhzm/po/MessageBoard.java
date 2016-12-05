package com.scxh.yhzm.po;
//留言实体
public class MessageBoard {
	String msId;
	String msContent;//留言的内容
	Boolean isDraft;//是否为草稿
	
	public String getMsId() {
		return msId;
	}
	public void setMsId(String msId) {
		this.msId = msId;
	}
	public String getMsContent() {
		return msContent;
	}
	public void setMsContent(String msContent) {
		this.msContent = msContent;
	}
	public Boolean getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Boolean isDraft) {
		this.isDraft = isDraft;
	}
	
	
}
