package com.yifengblog.twitter.ws.tweet.shared.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.yifengblog.twitter.ws.user.ui.request.TweetType;

public class TweetDTO implements Serializable 
{
	private static final long serialVersionUID = 633033429067566445L;
	private long id;
	private String tweetId;
	private String userId;
	private String screenName;
	private String userEmail;
	private Timestamp createdAt;
	private String text;
	private UserDTO userDetails;
	private TweetType type;
	
	public TweetType getType() {
		return type;
	}
	public void setType(TweetType type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public UserDTO getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}
}