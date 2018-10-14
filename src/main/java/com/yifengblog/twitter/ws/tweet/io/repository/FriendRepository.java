package com.yifengblog.twitter.ws.tweet.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yifengblog.twitter.ws.tweet.io.entity.FollowEntity;

@Repository
public interface FriendRepository extends CrudRepository<FollowEntity, Long>{
    List<FollowEntity> findByFolloweeId(long followeeId);
}
