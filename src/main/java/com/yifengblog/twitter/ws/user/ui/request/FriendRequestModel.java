package com.yifengblog.twitter.ws.user.ui.request;

public class FriendRequestModel {
	private String userEmail;
	private String followeeEmail;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFolloweeEmail() {
		return followeeEmail;
	}
	public void setFolloweeEmail(String friendEmail) {
		this.followeeEmail = friendEmail;
	}
}
