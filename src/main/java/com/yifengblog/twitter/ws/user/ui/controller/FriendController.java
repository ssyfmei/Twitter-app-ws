package com.yifengblog.twitter.ws.user.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yifengblog.twitter.ws.tweet.service.FollowService;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;
import com.yifengblog.twitter.ws.user.ui.request.FriendRequestModel;
import com.yifengblog.twitter.ws.user.ui.response.UserResp;

@RestController
@RequestMapping("friends")
public class FriendController {
	
    @Autowired
    ModelMapper modelMapper;
    
	@Autowired
	FollowService friendService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
		         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public void addFriend(@RequestBody FriendRequestModel friendDetail) {
		
	    friendService.addFollowee(friendDetail.getUserEmail(), friendDetail.getFolloweeEmail());
	}
	
	
	@GetMapping(path="/followers/email/{userEmail}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserResp> getFollowersByEmail(@PathVariable String userEmail) {
        
	    List<UserResp> returnValue = new ArrayList<>();
	    List<UserDTO> userDTOs = friendService.getFollowersByEmail(userEmail);
	    
	    if(userDTOs != null && !userDTOs.isEmpty()) {
            for(UserDTO userDTO : userDTOs) {
                returnValue.add(modelMapper.map(userDTO, UserResp.class));
            }
        }
        return returnValue;
    }
	
	@DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public void deleteFriend(@RequestBody FriendRequestModel friendDetail) {
	    
	    friendService.deleteFriend(friendDetail.getUserEmail(), friendDetail.getFolloweeEmail());
	}
}
