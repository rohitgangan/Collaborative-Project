package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.Friend;

public interface FriendDAO {

	public boolean save(Friend friend);
	public List<Friend> getAllFriendRequests(int userId);
	public List<Friend> getAllFriends(int userId);
	public List<Friend> listFriends();
	public void update(int friendId, int userId);
	public void reject(int friendId, int userId);
}
