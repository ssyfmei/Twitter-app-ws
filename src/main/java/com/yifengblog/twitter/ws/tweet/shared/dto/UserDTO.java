package com.yifengblog.twitter.ws.tweet.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable 
{	
	private static final long serialVersionUID = 6050270626585592787L;
	
	private long id;
	private String userId;
	private String screenName;
	private String email;
	private String password;
	private String encryptedPassword;
	private List<TweetDTO> tweets;
	
	private String emailVerificationToken;
	private Boolean emailVerificationStatus;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public List<TweetDTO> getTweets() {
		return tweets;
	}
	public void setTweets(List<TweetDTO> tweets) {
		this.tweets = tweets;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
}
	
	
	