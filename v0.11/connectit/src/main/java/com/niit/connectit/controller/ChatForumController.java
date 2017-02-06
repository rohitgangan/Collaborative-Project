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

import com.niit.connectit.model.ChatForum;
import com.niit.connectit.service.ChatForumService;
import com.niit.connectit.service.UserService;

@RestController
public class ChatForumController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatForumService chatForumService;
	
	@RequestMapping(value= "/forumChat", method = RequestMethod.GET)
	public ResponseEntity<List<ChatForum>> listAllChatsByForum(HttpSession session){
		int forumId = (Integer) session.getAttribute("forumId");
		System.out.println("forumId is" + forumId);
		List<ChatForum> list = chatForumService.listAllChatsByForumId(forumId);
		return new ResponseEntity<List<ChatForum>>(list,HttpStatus.OK);
	}
	@RequestMapping(value= "/forumChat/", method = RequestMethod.POST)
	public ResponseEntity<Void> addChat(@RequestBody ChatForum chatForum,UriComponentsBuilder builder,HttpSession session){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	     chatForum.setUserId(userId);
	     int forumId = (Integer) session.getAttribute("forumId");
		 System.out.println("forumId is" + forumId);
		 chatForum.setForumId(forumId);
		 Date date = new Date();
		 chatForum.setForumChatDate(date);
		 boolean flag = chatForumService.addChatForum(chatForum);
         if (flag == false) {
  	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
         }
         HttpHeaders headers = new HttpHeaders();
         headers.setLocation(builder.path("/forumChat/{chatForumId}").buildAndExpand(chatForum.getChatForumId()).toUri());
         
         return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/forumChat/{chatForumId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteForumChat(@PathVariable("chatForumId") Integer chatForumId) {
		chatForumService.deleteForumChat(chatForumId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
