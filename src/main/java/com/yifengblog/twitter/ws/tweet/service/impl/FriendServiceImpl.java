package com.yifengblog.twitter.ws.tweet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yifengblog.twitter.ws.tweet.io.entity.FriendEntity;
import com.yifengblog.twitter.ws.tweet.io.entity.UserEntity;
import com.yifengblog.twitter.ws.tweet.io.repository.FriendRepository;
import com.yifengblog.twitter.ws.tweet.io.repository.UserRepository;
import com.yifengblog.twitter.ws.tweet.service.FriendService;
import com.yifengblog.twitter.ws.tweet.service.UserService;

@Service
public class FriendServiceImpl implements FriendService {
    
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	FriendRepository friendRepository;
	
	@Override
	public void addFriend(String userEmail, String friendEmail) {
		UserEntity userEntity = userRepository.findByEmail(userEmail);
		UserEntity userFriendEntity=userRepository.findByEmail(friendEmail);

		
		FriendEntity friendEntity = new FriendEntity();
		friendEntity.setUserId(userEntity);
		friendEntity.setFriendId(userFriendEntity.getId());
		
		friendRepository.save(friendEntity);
	}

	@Override
	public void deleteFriend(String userEmail, String friendEmail) {
		UserEntity userEntity = userRepository.findByEmail(userEmail);
		UserEntity userFriendEntity=userRepository.findByEmail(friendEmail);

		FriendEntity friendEntity = new FriendEntity();
		friendEntity.setId(userEntity.getId());
		friendEntity.setUserId(userEntity);
		friendEntity.setFriendId(userFriendEntity.getId());
		friendRepository.delete(friendEntity);
	}

}
