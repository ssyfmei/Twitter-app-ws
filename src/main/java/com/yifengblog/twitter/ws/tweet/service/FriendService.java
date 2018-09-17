package com.yifengblog.twitter.ws.tweet.service;

public interface FriendService {
	void addFriend(String userEmail, String friendEmail);
	void deleteFriend(String userEmail, String friendEmail);
}
