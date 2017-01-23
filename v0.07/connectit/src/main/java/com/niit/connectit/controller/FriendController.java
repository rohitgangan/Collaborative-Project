package com.niit.connectit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.connectit.model.Friend;
import com.niit.connectit.service.FriendService;
import com.niit.connectit.service.UserService;

@RestController
public class FriendController {

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/sendFriendRequest/{friendId}", method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Friend friend, @PathVariable("friendId") Integer friendId, UriComponentsBuilder builder){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	      friend.setUserId(userId);
	      friend.setFriendId(friendId);
	      friend.setStatus('N');
	      friend.setIsOnline('N');
	      boolean flag = friendService.save(friend);
          if (flag == false) {
   	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
          }
          HttpHeaders headers = new HttpHeaders();
          headers.setLocation(builder.path("/sendFriendRequest/{friendId}").buildAndExpand(friend.getFriendId()).toUri());
          return new ResponseEntity<Void>(headers, HttpStatus.CREATED);	
	}
}
