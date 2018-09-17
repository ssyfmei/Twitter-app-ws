package com.yifengblog.twitter.ws.tweet.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yifengblog.twitter.ws.user.ui.request.TweetType;

import java.sql.Timestamp;

@Entity(name="tweets")
public class TweetEntity implements Serializable
{
	private static final long serialVersionUID = -1080747028108171668L;
	@Id
	@GeneratedValue  // need revision
	private long id;
	
	@Column(nullable=false, length=18)
	private String type;
	
	@Column(nullable=false, unique=true, length=40)
	private String tweetId;
	
	@Column(nullable=false, length=128)
	private String screenName;
	
	@Column(nullable=false)
	private Timestamp createdAt;
	
	@Column(nullable=false)
	private String text;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity userDetails;
	
	
	public UserEntity getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
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
	
	public TweetType getType() {
		return TweetType.valueOf(type);
	}
	
	public void setType(TweetType type) {
		this.type = type.name();
	}
	
}
