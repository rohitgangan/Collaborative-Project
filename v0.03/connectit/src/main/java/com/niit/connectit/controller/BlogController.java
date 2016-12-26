package com.niit.connectit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.connectit.model.Blog;
import com.niit.connectit.service.BlogService;

@RestController
public class BlogController {
		
		@Autowired
		private BlogService blogService;
		
		@RequestMapping(value= "/blog/{blogId}", method = RequestMethod.GET)
		public ResponseEntity<Blog> getBlogById(@PathVariable("blogId") Integer blogId){
			
			Blog blog = blogService.getBlogById(blogId);
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			
		}
		
		@RequestMapping(value= "/blog", method = RequestMethod.POST)
		public ResponseEntity<Void> addBlog(@RequestBody Blog blog, @RequestParam(value = "mf") MultipartFile mf, UriComponentsBuilder builder) {
	        boolean flag = blogService.addBlog(blog);
	               if (flag == false) {
	        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	               }
	               HttpHeaders headers = new HttpHeaders();
	               headers.setLocation(builder.path("/blog/{blogId}").buildAndExpand(blog.getBlogId()).toUri());
	               
	               File file;
	       		String path = "E:\\NIIT DigiNxt\\Project2\\connectit-frontend\\src\\main\\webapp\\resources\\images\\";
	       		path = path+String.valueOf(blog.getBlogId())+".jpg";
	       		file = new File(path);
	       		System.out.println(path);
	       		 mf = blog.getImage();
	       		if(!mf.isEmpty())
	       		{
	       			try {
	       				byte[] bytes = mf.getBytes();
	       				FileOutputStream fos = new FileOutputStream(file);
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
	               
	               
	               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		
		@RequestMapping(value= "/blog", method = RequestMethod.GET)
		public ResponseEntity<List<Blog>> listBlog() {
			List<Blog> list = blogService.listBlog();
			return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);
		}
		
		@RequestMapping(value="/blog/{blogId}", method = RequestMethod.PUT )
		public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
			blogService.updateBlog(blog);
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		
		@RequestMapping(value="/blog/{blogId}", method = RequestMethod.DELETE )
		public ResponseEntity<Void> deleteBlog(@PathVariable("blogId") Integer blogId) {
			blogService.deleteBlog(blogId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
}
