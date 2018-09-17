package com.yifengblog.twitter.ws.user.ui.controller;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yifengblog.twitter.ws.tweet.service.TweetService;
import com.yifengblog.twitter.ws.tweet.shared.dto.TweetDTO;
import com.yifengblog.twitter.ws.user.ui.request.TweetDetailsRequestModel;
import com.yifengblog.twitter.ws.user.ui.request.TweetUpdateRequestModel;
import com.yifengblog.twitter.ws.user.ui.response.TweetResp;

@RestController
@RequestMapping("tweets")
public class TweetController {

	@Autowired
	TweetService tweetService;
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public TweetResp postTweet(@RequestBody TweetDetailsRequestModel tweetDetails) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		TweetDTO tweetDto = modelMapper.map(tweetDetails, TweetDTO.class);
		tweetDto.setCreatedAt(timestamp);
		
		TweetDTO postedTweet = tweetService.postTweet(tweetDto);
		TweetResp postedResponse = modelMapper.map(postedTweet, TweetResp.class); 
		return postedResponse;
	}
	
	@PutMapping(path = "/{tweetId}", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public TweetResp updateUser(@PathVariable String tweetId, 
			                    @RequestBody TweetUpdateRequestModel tweetDetails) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		TweetDTO tweetDto = modelMapper.map(tweetDetails, TweetDTO.class);
		tweetDto.setCreatedAt(timestamp);
		tweetDto.setTweetId(tweetId);
		TweetDTO updatedDto = tweetService.updateTweet(tweetDto);
		
		return modelMapper.map(updatedDto, TweetResp.class);
	}
	
	@GetMapping(path = "/{tweetId}",  
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public TweetResp getUser(@PathVariable String tweetId)
	{
		TweetDTO tweetDto = tweetService.getTweetById(tweetId);
		
		return modelMapper.map(tweetDto, TweetResp.class);
	}
	
	@DeleteMapping(path="/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) 
	public void deletePost() {
		
		
	}
}
