package com.scxh.yhzm.po;
//回复内容实体
public class Reply {
	private String replyId;
	private String replyContent;//回复内容
	private Boolean isDraft;//是否为草稿
	private MessageBoard messageBoard;//外键
	
	
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Boolean getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Boolean isDraft) {
		this.isDraft = isDraft;
	}
	public MessageBoard getMessageBoard() {
		return messageBoard;
	}
	public void setMessageBoard(MessageBoard messageBoard) {
		this.messageBoard = messageBoard;
	}
	
	
}
