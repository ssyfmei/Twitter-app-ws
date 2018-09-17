package com.yifengblog.twitter.ws.tweet.io.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.yifengblog.twitter.ws.tweet.io.entity.TweetEntity;
import com.yifengblog.twitter.ws.tweet.io.entity.UserEntity;

@Repository
public interface TweetRepository extends PagingAndSortingRepository<TweetEntity, Long> {
	TweetEntity findByTweetId(String tweetId);
	List<TweetEntity> findAllByUserDetails(UserEntity userDetails);
}