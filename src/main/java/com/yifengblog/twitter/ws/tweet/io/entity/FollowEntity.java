package com.yifengblog.twitter.ws.tweet.io.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="friends")
public class FollowEntity {
    
	@Id
	@GeneratedValue  // need revision later
	private long id;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity userId;
	
	private long followeeId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public long getFolloweeId() {
		return followeeId;
	}
	
	public void setFolloweeId(long friendId) {
		this.followeeId = friendId;
	}

}