package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.BlogDAO;
import com.niit.connectit.model.Blog;

@Repository
public class BlogDAOImpl implements BlogDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addBlog(Blog blog) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Blog> listBlog() {
		// TODO Auto-generated method stub
		List<Blog> blogList = this.sessionFactory.getCurrentSession().createQuery("from Blog").getResultList();
		return blogList;
	}

	@SuppressWarnings("unchecked")
	public Blog getBlogById(int blogId) {
		// TODO Auto-generated method stub
		String hql = "from Blog where blogId = "+blogId;
		List<Blog> blogList = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if(blogList != null && !blogList.isEmpty())
		{
			return blogList.get(0);
		}else{
			return null;
		}
	}

	public void deleteBlog(int blogId) {
		// TODO Auto-generated method stub
		Blog blogDelete = new Blog();
		blogDelete.setBlogId(blogId);
		this.sessionFactory.getCurrentSession().delete(blogDelete);
	}

	

	public void updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		Blog b = getBlogById(blog.getBlogId());
		b.setBlogName(blog.getBlogName());
		b.setBlogContent(blog.getBlogContent());
		sessionFactory.getCurrentSession().update(b);
	}

	@SuppressWarnings("unchecked")
	public boolean blogExists(String blogName) {
		// TODO Auto-generated method stub
		String hql = "from Blog where blogName='"+blogName+"'";
		List<Blog> blogs = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return blogs.size()>0 ? true : false;
	}

	

}
