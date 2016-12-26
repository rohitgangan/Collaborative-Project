package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.Blog;

public interface BlogDAO {
	public boolean addBlog(Blog blog);
	public List<Blog> listBlog();
	public Blog getBlogById(int blogId);
	public void deleteBlog(int blogId);
	public boolean blogExists(String blogName);
	
	  /*Blog findById(long id);
	     
	    Blog findByName(String blogName);
	     
	    void saveBlog(Blog blog);
	     
	    void updateBlog(Blog blog);
	     
	    void deleteUserById(long id);
	 
	    List<Blog> findAllUsers(); 
	     
	    void deleteAllUsers();
	     
	    public boolean isUserExist(Blog blog);*/
}
