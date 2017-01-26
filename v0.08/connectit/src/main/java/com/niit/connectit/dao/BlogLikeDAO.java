package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.BlogLike;

public interface BlogLikeDAO {
	
	public boolean addBlogLike(BlogLike blogLike);
	public List<BlogLike> listAllLikesByBlogId(int blogId);
	public void deleteBlogLike(int blogId, int userId);
	public boolean blogExists(int userId, int blogId);
	public void updateBlogLike(BlogLike blogLike);
	public BlogLike getBlogLikeById(int userId, int blogId);
}
