package com.yifengblog.twitter.ws.tweet.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yifengblog.twitter.ws.tweet.shared.dto.TweetDTO;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;

public interface UserService extends UserDetailsService {
	public UserDTO getUserByEmail(String email);
	public UserDTO createUser(UserDTO userDto);
	public List<TweetDTO> getTweets(String userId);
	public UserDTO updateUser(String id, UserDTO userDto);
	public void deleteUser(String userId);
	public UserDTO getUser(String userId);
    public boolean verifyEmailToken(String token);
    public boolean requestPasswordReset(String email);
    public boolean resetPassword(String token, String password);
    public List<TweetDTO> getTweetsByEmail(String userEmail);
}