package com.niit.connectit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.FriendDAOImpl;
import com.niit.connectit.model.Friend;

@Service
public class FriendService {

	@Autowired
	private FriendDAOImpl friendDAO;
	
	@Transactional
	public synchronized boolean save(Friend friend) {
		friendDAO.save(friend);
		return true;
		
	}
}
