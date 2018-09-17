package com.yifengblog.twitter.ws.tweet.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yifengblog.twitter.ws.tweet.io.entity.FriendEntity;

@Repository
public interface FriendRepository extends CrudRepository<FriendEntity, Long>{
}
