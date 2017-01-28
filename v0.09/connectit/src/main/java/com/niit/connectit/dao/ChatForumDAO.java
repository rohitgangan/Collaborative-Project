package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.ChatForum;

public interface ChatForumDAO {

	public boolean addChatForum(ChatForum chatForum);
	public List<ChatForum> listAllChatsByForumId(int forumId);
	public void deleteForumChat(int chatForumId);
}
