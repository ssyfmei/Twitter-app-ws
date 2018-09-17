package com.yifengblog.twitter.ws.tweet.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yifengblog.twitter.ws.tweet.shared.dto.TweetDTO;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;

public interface UserService extends UserDetailsService {
	public UserDTO getUser(String email);
	public UserDTO createUser(UserDTO userDto);
	public List<TweetDTO> getTweets(String userId);
	public UserDTO updateUser(String id, UserDTO userDto);
	public void deleteUser(String userId);
	public UserDTO getUserByUserId(String userId);
}