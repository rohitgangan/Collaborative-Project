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

import com.niit.connectit.model.BlogLike;
import com.niit.connectit.service.BlogLikeService;
import com.niit.connectit.service.UserService;

@RestController
public class BlogLikeController {
	@Autowired
	private BlogLikeService blogLikeService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value= "/blogLike", method = RequestMethod.GET)
	public ResponseEntity<List<BlogLike>> listAllLikesByBlog(HttpSession session) {
		int blogId=(Integer) session.getAttribute("blogId");
		List<BlogLike> list = blogLikeService.listAllLikesByBlogId(blogId);
		return new ResponseEntity<List<BlogLike>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/blogLike/", method = RequestMethod.POST)
	public ResponseEntity<Void> addBlog(@RequestBody BlogLike blogLike,UriComponentsBuilder builder,HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	      blogLike.setUserId(userId);
	      int blogId=(Integer) session.getAttribute("blogId");
	      System.out.println("blog id is :"+blogId);
	      blogLike.setBlogId(blogId);
	      
		boolean flag = blogLikeService.addBlogLike(blogLike);
               if (flag == false) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blogLike/{blogLikeId}").buildAndExpand(blogLike.getBlogLikeId()).toUri());
               
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/blogLike/{blogId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBlogComment(@PathVariable("blogId") Integer blogId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
		blogLikeService.deleteBlogLike(blogId,userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
