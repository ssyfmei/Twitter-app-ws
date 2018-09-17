package com.yifengblog.twitter.ws.user.ui.request;

public class FriendRequestModel {
	private String userEmail;
	private String friendEmail;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFriendEmail() {
		return friendEmail;
	}
	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}
}
