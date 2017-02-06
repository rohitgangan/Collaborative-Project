package com.niit.connectit.controller;

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

import com.niit.connectit.model.ForumMember;
import com.niit.connectit.service.ForumMemberService;
import com.niit.connectit.service.UserService;

@RestController
public class ForumMemberController {

	@Autowired
	private ForumMemberService forumMemberService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/forumMember", method = RequestMethod.GET)
	public ResponseEntity<List<ForumMember>> listAllMembersByForum(HttpSession session){
		int forumId = (Integer) session.getAttribute("forumId");
		System.out.println("forumId is" + forumId);
		List<ForumMember> list = forumMemberService.listAllMembersByForum(forumId);
		return new ResponseEntity<List<ForumMember>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value= "/forumMember/", method = RequestMethod.POST)
	public ResponseEntity<Void> addForumMember(@RequestBody ForumMember forumMember,UriComponentsBuilder builder,HttpSession session){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	     forumMember.setUserId(userId);
	     int forumId = (Integer) session.getAttribute("forumId");
		 System.out.println("forumId is" + forumId);
		 forumMember.setForumId(forumId);
		 boolean flag = forumMemberService.addForumMember(forumMember);
         if (flag == false) {
  	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
         }
         HttpHeaders headers = new HttpHeaders();
         headers.setLocation(builder.path("/forumMember/{forumMemberId}").buildAndExpand(forumMember.getForumMemberId()).toUri());
         
         return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/forumMember/{forumMemberId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteForumMember(@PathVariable("forumMemberId") Integer forumMemberId) {
		forumMemberService.deleteForumMember(forumMemberId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
