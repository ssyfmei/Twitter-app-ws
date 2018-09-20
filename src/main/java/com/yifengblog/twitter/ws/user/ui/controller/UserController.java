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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yifengblog.twitter.ws.tweet.service.UserService;
import com.yifengblog.twitter.ws.tweet.shared.dto.TweetDTO;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;
import com.yifengblog.twitter.ws.user.ui.request.UserDetailsRequestModel;
import com.yifengblog.twitter.ws.user.ui.response.TweetResp;
import com.yifengblog.twitter.ws.user.ui.response.UserResp;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserService userService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	             produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserResp createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserDTO userDto = modelMapper.map(userDetails, UserDTO.class);
		UserDTO  createUser = userService.createUser(userDto);
		UserResp returnValue = modelMapper.map(createUser, UserResp.class);
		return returnValue;
	}
	
	@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserResp getUser(@PathVariable String userId) {
		UserDTO userDto = userService.getUser(userId);
		return modelMapper.map(userDto, UserResp.class);
	}
	
	@PutMapping(path="/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			                  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserResp updateUser(@PathVariable String userId, @RequestBody UserDetailsRequestModel userDetails) {
		UserDTO userDto = modelMapper.map(userDetails, UserDTO.class);
		UserDTO updatedUser = userService.updateUser(userId, userDto);
		UserResp returnValue = modelMapper.map(updatedUser, UserResp.class);
		return returnValue; 
	}
	
	@DeleteMapping(path="/{userId}")
	public String deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
		return "Success";
	}
	
	@GetMapping(path="/{userId}/tweets", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<TweetResp> getTweets(@PathVariable String userId) {
        
        
        
        
        List<TweetResp> returnValue = new ArrayList<>();
        List<TweetDTO>  tweetDtos = userService.getTweets(userId);
        
        if(tweetDtos != null && !tweetDtos.isEmpty()) {
            for(TweetDTO tweetDto : tweetDtos) {
                returnValue.add(modelMapper.map(tweetDto, TweetResp.class));
            }
        }
        return returnValue;
    }
	
	
}