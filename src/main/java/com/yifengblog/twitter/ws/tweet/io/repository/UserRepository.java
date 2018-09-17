package com.yifengblog.twitter.ws.tweet.io.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yifengblog.twitter.ws.tweet.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}