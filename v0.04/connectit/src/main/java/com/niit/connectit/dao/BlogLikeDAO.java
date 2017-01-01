package com.niit.connectit.dao;

import java.util.List;


import com.niit.connectit.model.BlogLike;

public interface BlogLikeDAO {
	
	public boolean addBlogLike(BlogLike blogLike);
	public List<BlogLike> listAllCommentsByBlogId(int blogId);
	public void deleteBlogLike(int blogLikeId);
	public boolean blogExists(int userId, int blogId);
}
