package com.yifengblog.twitter.ws.user.ui.request;

public class TweetUpdateRequestModel {
	private String text;
	private TweetType type;
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
