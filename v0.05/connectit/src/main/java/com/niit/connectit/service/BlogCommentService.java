package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.BlogCommentDAOImpl;
import com.niit.connectit.model.BlogComment;

@Service
public class BlogCommentService {
	
	@Autowired
	private BlogCommentDAOImpl blogCommentDAO;
	@Transactional
	public boolean addBlog(BlogComment blogComment){
		blogCommentDAO.addBlog(blogComment);
		return true;
		
	}
	@Transactional
	public List<BlogComment> listAllCommentsByBlogId(int blogId) {
		return blogCommentDAO.listAllCommentsByBlogId(blogId);
		
	}
	
	@Transactional
	public void deleteBlogComment(int commentId) {
		blogCommentDAO.deleteBlogComment(commentId);
	}
	
}
