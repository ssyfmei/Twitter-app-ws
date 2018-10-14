package com.yifengblog.twitter.ws.tweet.service;

import java.util.List;

import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;

public interface FollowService {
	void addFollowee(String userEmail, String followeeEmail);
	void addFolloweeById(String userID, String followeeID);
	void deleteFriend(String userEmail, String followerEmail);
    List<UserDTO> getFollowersByEmail(String userEmail);
}