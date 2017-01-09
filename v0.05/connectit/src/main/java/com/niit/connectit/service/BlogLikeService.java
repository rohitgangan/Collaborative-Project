package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.BlogLikeDAOImpl;
import com.niit.connectit.model.BlogLike;

@Service
public class BlogLikeService {

	@Autowired
	private BlogLikeDAOImpl blogLikeDAO;
	@Transactional
	public boolean addBlogLike(BlogLike blogLike) {
		if(blogLikeDAO.blogExists(blogLike.getUserId(), blogLike.getBlogId())){
			return false;
		}else{
			blogLikeDAO.addBlogLike(blogLike);
			return true;
		}
	}
	
	public List<BlogLike> listAllLikesByBlogId(int blogId) {
		return blogLikeDAO.listAllLikesByBlogId(blogId);
	}
	
	public void deleteBlogLike(int blogId, int userId) {
		blogLikeDAO.deleteBlogLike(blogId, userId);
	}
}
