package com.niit.connectit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.connectit.model.NewUser;
import com.niit.connectit.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/user/{uid}", method = RequestMethod.GET)
	public ResponseEntity<NewUser> getUserById(@PathVariable("uid") Integer uid){
		
		NewUser user = userService.getUserById(uid);
		return new ResponseEntity<NewUser>(user, HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> addUSer(@RequestBody NewUser user, UriComponentsBuilder builder) {
        boolean flag = userService.addUSer(user);
               if (flag == false) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/user/{uid}").buildAndExpand(user.getUserId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value= "/user", method = RequestMethod.GET)
	public ResponseEntity<List<NewUser>> listUser() {
		List<NewUser> list = userService.listUser();
		return new ResponseEntity<List<NewUser>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{uid}", method = RequestMethod.PUT )
	public ResponseEntity<NewUser> updateUser(@RequestBody NewUser user) {
		userService.updateUser(user);
		return new ResponseEntity<NewUser>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{uid}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteUser(@PathVariable("uid") Integer uid) {
		userService.deleteUser(uid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
