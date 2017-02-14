package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.Forum;

public interface ForumDAO {

	public boolean addForum(Forum forum);
	public List<Forum> listForum();
	public List<Forum> listUserForum(int userId);
	public Forum getForumById(int forumId);
	public void deleteForum(int forumId);
	public boolean forumExist(String forumName);
	public void updateForum(Forum forum);
}
