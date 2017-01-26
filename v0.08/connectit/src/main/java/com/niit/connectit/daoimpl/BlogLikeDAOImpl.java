package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.BlogLikeDAO;
import com.niit.connectit.model.BlogLike;

@Repository
public class BlogLikeDAOImpl implements BlogLikeDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addBlogLike(BlogLike blogLike) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(blogLike);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<BlogLike> listAllLikesByBlogId(int blogId) {
		// TODO Auto-generated method stub
		String hql = "from BlogLike where blogId="+blogId;
		List<BlogLike> likes = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return likes;
	}
	
	public void deleteBlogLike(int blogId, int userId) {
		// TODO Auto-generated method stub
		BlogLike likeDelete = new BlogLike();
		likeDelete.setBlogId(blogId);
		likeDelete.setUserId(userId);
		this.sessionFactory.getCurrentSession().delete(likeDelete);
	}

	@SuppressWarnings("unchecked")
	public boolean blogExists(int userId, int blogId) {
		// TODO Auto-generated method stub
		String hql = "from BlogLike where blogId=" + blogId+"and userId="+userId;
		List<BlogLike> likes = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return likes.size()>0 ? true : false;
	}

	public void updateBlogLike(BlogLike blogLike) {
		// TODO Auto-generated method stub
		
		/*BlogLike b = getBlogById(blog.getBlogId());
		b.setBlogName(blog.getBlogName());
		b.setBlogContent(blog.getBlogContent());
		sessionFactory.getCurrentSession().update(b);*/
	}

	@SuppressWarnings("unchecked")
	public BlogLike getBlogLikeById(int userId, int blogId) {
		// TODO Auto-generated method stub
		String hql = "from BlogLike where blogId=" + blogId+"and userId="+userId;
		List<BlogLike> likeList = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if(likeList != null && !likeList.isEmpty())
		{
			return likeList.get(0);
		}else{
			return null;
		}
	}

}
