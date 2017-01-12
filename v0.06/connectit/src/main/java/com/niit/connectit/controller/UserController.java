package com.niit.connectit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
	
	@RequestMapping(value= "/myProfile", method = RequestMethod.GET)
	public ResponseEntity<NewUser> getUserProfileById(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	      System.out.println("id is"+ userId);
	      NewUser user = userService.getUserById(userId);
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
	
	//picture upload
	@RequestMapping(value= "/user/profileUpload", method = RequestMethod.POST)
	public ResponseEntity<Void> addUserProfilePicture(@RequestParam(value="file")MultipartFile file,UriComponentsBuilder builder,NewUser user){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	      System.out.println("id is"+ userId);
	      File newFile;
     		String path = "F:\\Collaborative Project\\connectit-frontend\\src\\main\\webapp\\resources\\images\\user\\";
     		path = path+userId+".jpg";
     		newFile = new File(path);
     		System.out.println(path);
     		
     		if(!file.isEmpty())
     		{
     			try {
     				byte[] bytes = file.getBytes();
     				FileOutputStream fos = new FileOutputStream(newFile);
     				BufferedOutputStream bos = new BufferedOutputStream(fos);
     				bos.write(bytes);
     				bos.close();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				System.out.println("exception occured"+e);
     			}			
     		}
     		else{
     				System.out.println("No file selected");
     		}
     		
     		 HttpHeaders headers = new HttpHeaders();
             headers.setLocation(builder.path("/user/{userId}").buildAndExpand(userId).toUri());
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
