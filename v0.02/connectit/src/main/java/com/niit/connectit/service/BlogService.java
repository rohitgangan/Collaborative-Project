package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.BlogDAOImpl;
import com.niit.connectit.model.Blog;

@Service
public class BlogService {
	
	@Autowired
	private BlogDAOImpl blogDAOImpl;
	
	@Transactional
	public void setBlogDAO(BlogDAOImpl blogDAOImpl){
			this.blogDAOImpl = blogDAOImpl;
		}
	
	@Transactional
	public synchronized boolean addBlog(Blog blog) {
			if(blogDAOImpl.blogExists(blog.getBlogName())){
				return false;
			}else{
				blogDAOImpl.addBlog(blog);
				return true;
			}
		}
	
	@Transactional
	public List<Blog> listBlog() {
		return blogDAOImpl.listBlog();
		
		}
	
	@Transactional
	public Blog getBlogById(int blogId) {
			return blogDAOImpl.getBlogById(blogId);
		}
	
	@Transactional
	public void deleteBlog(int blogId) {
		blogDAOImpl.deleteBlog(blogId);
	}
	
	@Transactional
	public void updateBlog(Blog blog) {
		blogDAOImpl.updateBlog(blog);
	}
}
