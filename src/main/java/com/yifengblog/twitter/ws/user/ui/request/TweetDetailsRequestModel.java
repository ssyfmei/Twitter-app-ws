package com.yifengblog.twitter.ws.user.ui.request;

public class TweetDetailsRequestModel {
	private String userEmail;
	private String text;
	private TweetType type;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public TweetType getType() {
		return type;
	}
	public void setType(String type) {
		this.type = TweetType.valueOf(type);
	}
	
}