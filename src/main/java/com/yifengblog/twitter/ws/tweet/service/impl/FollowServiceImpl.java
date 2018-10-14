package com.yifengblog.twitter.ws.tweet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yifengblog.twitter.ws.tweet.io.entity.FollowEntity;
import com.yifengblog.twitter.ws.tweet.io.entity.UserEntity;
import com.yifengblog.twitter.ws.tweet.io.repository.FriendRepository;
import com.yifengblog.twitter.ws.tweet.io.repository.UserRepository;
import com.yifengblog.twitter.ws.tweet.service.FollowService;
import com.yifengblog.twitter.ws.tweet.service.UserService;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;

@Service
public class FollowServiceImpl implements FollowService {
    
    @Autowired
    ModelMapper modelMapper;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	FriendRepository friendRepository;
	
	@Override
	public void addFollowee(String userEmail, String followeeEmail) {
		UserEntity userEntity = userRepository.findByEmail(userEmail);
		UserEntity userFriendEntity=userRepository.findByEmail(followeeEmail);
		
		FollowEntity followeeEntity = new FollowEntity();
		followeeEntity.setUserId(userEntity);
		followeeEntity.setFolloweeId(userFriendEntity.getId());
		
		friendRepository.save(followeeEntity);
	}
	
	@Override
    public void addFolloweeById(String userID, String followeeID) {
	    UserEntity userEntity = userRepository.findByUserId(userID);
        UserEntity userFriendEntity=userRepository.findByUserId(followeeID);
        FollowEntity followeeEntity = new FollowEntity();
        followeeEntity.setUserId(userEntity);
        followeeEntity.setFolloweeId(userFriendEntity.getId());
        
        friendRepository.save(followeeEntity);
    }

	@Override
	public void deleteFriend(String userEmail, String friendEmail) {
		UserEntity userEntity = userRepository.findByEmail(userEmail);
		UserEntity userFriendEntity= userRepository.findByEmail(friendEmail);

		FollowEntity friendEntity = new FollowEntity();
		friendEntity.setId(userEntity.getId());
		friendEntity.setUserId(userEntity);
		friendEntity.setFolloweeId(userFriendEntity.getId());
		friendRepository.delete(friendEntity);
	}

    @Override
    public List<UserDTO> getFollowersByEmail(String userEmail) {
        List<UserDTO> returnValue = new ArrayList<>();
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        long id = userEntity.getId();
        List<FollowEntity> followers = friendRepository.findByFolloweeId(id);
        
        for(FollowEntity entity: followers) {
            returnValue.add(modelMapper.map(entity.getUserId(), UserDTO.class));
        }
        return returnValue;
    }
}
