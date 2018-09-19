package com.yifengblog.twitter.ws.tweet.service;

import com.yifengblog.twitter.ws.tweet.shared.dto.TweetDTO;

public interface TweetService {
	TweetDTO postTweet(TweetDTO tweetDto);
	TweetDTO updateTweet(TweetDTO tweetDto);
	TweetDTO getTweet(String tweetId);
    void deleteTweet(String tweetId);
}