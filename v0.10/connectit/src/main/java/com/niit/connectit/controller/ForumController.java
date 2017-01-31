package com.niit.connectit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.niit.connectit.model.Forum;
import com.niit.connectit.service.ForumService;
import com.niit.connectit.service.UserService;

@RestController
public class ForumController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ForumService forumService;
	
	@RequestMapping(value= "/forum/{forumId}", method = RequestMethod.GET)
	public ResponseEntity<Forum> getForumById(@PathVariable("forumId") Integer forumId,HttpSession session){
		
		Forum forum = forumService.getForumById(forumId);
		session.setAttribute("forumId", forumId);
		int forum_Id=(Integer) session.getAttribute("forumId");
	      System.out.println("id is :"+forum_Id);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/forum", method = RequestMethod.POST)
	public ResponseEntity<Void> addForum(@RequestBody Forum forum,  UriComponentsBuilder builder) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	      forum.setUserId(userId);
	      Date date = new Date();
	      forum.setForumCreatedDate(date);
		boolean flag = forumService.addForum(forum);
               if (flag == false) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/forum/{forumId}").buildAndExpand(forum.getForumId()).toUri());
         
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value= "/forum", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> listForum() {
		List<Forum> list = forumService.listForum();
		return new ResponseEntity<List<Forum>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/forum/{forumId}", method = RequestMethod.PUT )
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	      forum.setUserId(userId);
		forumService.updateForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	@RequestMapping(value="/forum/{forumId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteForum(@PathVariable("forumId") Integer forumId) {
		forumService.deleteForum(forumId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	
}
