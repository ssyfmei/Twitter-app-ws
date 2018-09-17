package com.yifengblog.twitter.ws.tweet.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="users")
public class UserEntity implements Serializable 
{
	private static final long serialVersionUID = -3875010729963304780L;
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, unique=true)
	private String userId;
	
	@Column(nullable=false, unique=true, length=120)
	private String email;
	
	@Column(nullable=false, length=100)
	private String screenName;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	private String emailVerificationToken;
	
	@Column(nullable=true, columnDefinition = "boolean default false")
	private Boolean emailVerificationStatus;
	
	@OneToMany(mappedBy="userDetails", cascade=CascadeType.ALL)
	private List<TweetEntity> tweets;
	
	@OneToMany(mappedBy="userId", cascade=CascadeType.ALL)
	private List<FriendEntity> friends;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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

	public List<TweetEntity> getTweets() {
		return tweets;
	}
	
	public void setTweets(List<TweetEntity> tweets) {
		this.tweets = tweets;
	}

}
