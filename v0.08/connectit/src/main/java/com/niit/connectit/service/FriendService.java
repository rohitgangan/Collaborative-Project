package com.niit.connectit.service;

import java.util.List;

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
	
	@Transactional
	public List<Friend> getAllFriendRequests(int userId) {
		return friendDAO.getAllFriendRequests(userId);
		
	}
	
	@Transactional
	public void update(int friendId, int userId) {
		friendDAO.update(friendId, userId);
	}
}
