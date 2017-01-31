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

import com.niit.connectit.model.BlogComment;
import com.niit.connectit.service.BlogCommentService;
import com.niit.connectit.service.UserService;

@RestController
public class BlogCommentController {
	
	@Autowired
	private BlogCommentService blogCommentService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/blogComment", method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> listAllCommentsByBlog(HttpSession session) {
		int blogId=(Integer) session.getAttribute("blogId");
		List<BlogComment> list = blogCommentService.listAllCommentsByBlogId(blogId);
		return new ResponseEntity<List<BlogComment>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/blogComment/", method = RequestMethod.POST)
	public ResponseEntity<Void> addBlog(@RequestBody BlogComment blogComment,UriComponentsBuilder builder,HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	      blogComment.setUserId(userId);
	      int blogId=(Integer) session.getAttribute("blogId");
	      System.out.println("blog id is :"+blogId);
	      blogComment.setBlogId(blogId);
	      Date date = new Date();
	      blogComment.setCommentDate(date);
		boolean flag = blogCommentService.addBlog(blogComment);
               if (flag == false) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blogblogComment/{commentId}").buildAndExpand(blogComment.getCommentId()).toUri());
               
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/blogComment/{commentId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBlogComment(@PathVariable("commentId") Integer commentId) {
		blogCommentService.deleteBlogComment(commentId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
