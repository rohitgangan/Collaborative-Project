package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.BlogCommentDAO;
import com.niit.connectit.model.BlogComment;

@Repository
public class BlogCommentDAOImpl implements BlogCommentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addBlog(BlogComment blogComment) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<BlogComment> listAllCommentsByBlogId(int blogId) {
		// TODO Auto-generated method stub
		String hql = "from BlogComment where blogId="+blogId;
		List<BlogComment> comments = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return comments;
	}

	public void deleteBlogComment(int commentId) {
		// TODO Auto-generated method stub
		BlogComment commentDelete = new BlogComment();
		commentDelete.setCommentId(commentId);
		this.sessionFactory.getCurrentSession().delete(commentDelete);
	}

	

}
