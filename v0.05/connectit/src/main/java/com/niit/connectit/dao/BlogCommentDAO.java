package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.Blog;
import com.niit.connectit.model.BlogComment;

public interface BlogCommentDAO {
	
	public boolean addBlog(BlogComment blogComment);
	public List<BlogComment> listAllCommentsByBlogId(int blogId);
	public void deleteBlogComment(int commentId);
	
	
}
