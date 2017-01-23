package com.niit.connectit.daoimpl;

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
	
	
}
