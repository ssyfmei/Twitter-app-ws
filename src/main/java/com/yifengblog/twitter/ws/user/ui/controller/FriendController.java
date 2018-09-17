package com.yifengblog.twitter.ws.user.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yifengblog.twitter.ws.tweet.service.FriendService;
import com.yifengblog.twitter.ws.user.ui.request.FriendRequestModel;

@RestController
@RequestMapping("friends")
public class FriendController {
	
	@Autowired
	FriendService friendService;
	
	@PostMapping(
		consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public void addFriend(@RequestBody FriendRequestModel friendDetail) 
	{
		friendService.addFriend(friendDetail.getUserEmail(), friendDetail.getFriendEmail());
		friendService.addFriend(friendDetail.getFriendEmail(), friendDetail.getUserEmail());
	}
	
	@DeleteMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public void deleteFriend(@RequestBody FriendRequestModel friendDetail) {
		
		
	}
}
