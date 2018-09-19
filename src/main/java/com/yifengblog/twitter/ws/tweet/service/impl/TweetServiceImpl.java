package com.yifengblog.twitter.ws.tweet.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yifengblog.twitter.ws.tweet.io.entity.TweetEntity;
import com.yifengblog.twitter.ws.tweet.io.repository.TweetRepository;
import com.yifengblog.twitter.ws.tweet.service.TweetService;
import com.yifengblog.twitter.ws.tweet.service.UserService;
import com.yifengblog.twitter.ws.tweet.shared.Utils;
import com.yifengblog.twitter.ws.tweet.shared.dto.TweetDTO;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;

@Service
public class TweetServiceImpl implements TweetService 
{
	int tweetIdLen = 40;
	
	@Autowired
	Utils utils;
	@Autowired
	TweetRepository tweetRepository;
	@Autowired
	UserService userService;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public TweetDTO postTweet(TweetDTO tweetDto) {
		String tweetId = utils.generateTweetId(tweetIdLen);
		
		UserDTO userDetails = userService.getUserByEmail(tweetDto.getUserEmail());
		String userId = userDetails.getUserId();
		String screenName = userDetails.getScreenName();
		tweetDto.setUserDetails(userDetails);
		tweetDto.setUserId(userId);
		tweetDto.setScreenName(screenName);
		tweetDto.setTweetId(tweetId);
		
		TweetEntity tweetEntity = modelMapper.map(tweetDto, TweetEntity.class);
		TweetEntity savedTweet = tweetRepository.save(tweetEntity);
		TweetDTO savedDto = modelMapper.map(savedTweet, TweetDTO.class);
		return savedDto;
	}

	@Override
	public TweetDTO updateTweet(TweetDTO tweetDto) {
		String tweetId = tweetDto.getTweetId();
		TweetEntity tweet = tweetRepository.findByTweetId(tweetId);
		
		tweet.setCreatedAt(tweetDto.getCreatedAt());
		tweet.setType(tweetDto.getType());
		tweet.setText(tweetDto.getText());
		TweetEntity tweetEntity = tweetRepository.save(tweet);
		
		return modelMapper.map(tweetEntity, TweetDTO.class);
	}

	@Override
	public TweetDTO getTweet(String tweetId) {
		TweetEntity tweetEntity = tweetRepository.findByTweetId(tweetId);
		return modelMapper.map(tweetEntity, TweetDTO.class);
	}

    @Override
    public void deleteTweet(String tweetId) {
        TweetEntity tweetEntity = tweetRepository.findByTweetId(tweetId);
        tweetRepository.delete(tweetEntity);
    }

}