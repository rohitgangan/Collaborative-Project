package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.ForumDAO;
import com.niit.connectit.model.Forum;

@Repository
public class ForumDAOImpl implements ForumDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addForum(Forum forum) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Forum> listForum() {
		// TODO Auto-generated method stub
		List<Forum> forumList = this.sessionFactory.getCurrentSession().createQuery("from Forum").getResultList();
		return forumList;
	}

	@SuppressWarnings("unchecked")
	public Forum getForumById(int forumId) {
		// TODO Auto-generated method stub
		String hql = "from Forum where forumId = "+forumId;
		List<Forum> forumList = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if(forumList != null && !forumList.isEmpty())
		{
			return forumList.get(0);
		}else{
			return null;
		}
	}

	public void deleteForum(int forumId) {
		// TODO Auto-generated method stub
		Forum forumDelete = new Forum();
		forumDelete.setForumId(forumId);
		this.sessionFactory.getCurrentSession().delete(forumDelete);
	}

	public boolean forumExist(String forumName) {
		// TODO Auto-generated method stub
		String hql = "from Forum where forumName='"+forumName+"'";
		@SuppressWarnings("unchecked")
		List<Forum> forums = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return forums.size()>0 ? true : false;
	}

	public void updateForum(Forum forum) {
		// TODO Auto-generated method stub
		Forum f = getForumById(forum.getForumId());
		f.setForumName(forum.getForumName());
		f.setForumContent(forum.getForumContent());
		sessionFactory.getCurrentSession().update(f);
	}

	@SuppressWarnings("unchecked")
	public List<Forum> listUserForum(int userId) {
		// TODO Auto-generated method stub
		String hql = "from Forum where userId=" +userId ;
		List<Forum> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return list;
	}

}
