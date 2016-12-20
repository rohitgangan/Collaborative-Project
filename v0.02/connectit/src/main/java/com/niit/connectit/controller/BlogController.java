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

import com.niit.connectit.model.Blog;
import com.niit.connectit.service.BlogService;

@RestController
public class BlogController {
		
		@Autowired
		private BlogService blogService;
		
		@RequestMapping(value= "/blog/{id}", method = RequestMethod.GET)
		public ResponseEntity<Blog> getBlogById(@PathVariable("id") Integer id){
			
			Blog blog = blogService.getBlogById(id);
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			
		}
		
		@RequestMapping(value= "/blog", method = RequestMethod.POST)
		public ResponseEntity<Void> addPerson(@RequestBody Blog blog, UriComponentsBuilder builder) {
	        boolean flag = blogService.addBlog(blog);
	               if (flag == false) {
	        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	               }
	               HttpHeaders headers = new HttpHeaders();
	               headers.setLocation(builder.path("/blog/{id}").buildAndExpand(blog.getBlogId()).toUri());
	               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		
		@RequestMapping(value= "/blog", method = RequestMethod.GET)
		public ResponseEntity<List<Blog>> listBlog() {
			List<Blog> list = blogService.listBlog();
			return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);
		}
		
		@RequestMapping(value="/blog/{id}", method = RequestMethod.PUT )
		public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
			blogService.updateBlog(blog);
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		
		@RequestMapping(value="/blog/{id}", method = RequestMethod.DELETE )
		public ResponseEntity<Void> deleteBlog(@PathVariable("id") Integer id) {
			blogService.deleteBlog(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
}
