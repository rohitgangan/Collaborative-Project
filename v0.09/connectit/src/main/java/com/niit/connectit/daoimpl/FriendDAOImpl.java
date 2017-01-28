package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.FriendDAO;
import com.niit.connectit.model.Friend;

@Repository
public class FriendDAOImpl implements FriendDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Friend friend) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Friend> getAllFriendRequests(int userId) {
		// TODO Auto-generated method stub
		String hql = "from Friend where Status = 'N' and friendId="+userId;
		List<Friend> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList(); 
		return list;
	}

	public void update(int friendId, int userId) {
		// TODO Auto-generated method stub
		
		String hql="update Friend set Status='A' where userId=" +userId+"and friendId="+friendId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public void reject(int friendId, int userId) {
		// TODO Auto-generated method stub
		String hql="update Friend set Status='R' where userId=" +userId+"and friendId="+friendId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	
}
