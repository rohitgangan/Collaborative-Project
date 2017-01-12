package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.ChatForumDAOImpl;
import com.niit.connectit.model.ChatForum;

@Service
public class ChatForumService {
	
	@Autowired
	private ChatForumDAOImpl chatForumDAO;
	
	@Transactional
	public  boolean addChatForum(ChatForum chatForum) {
		
		chatForumDAO.addChatForum(chatForum);
		return true;
	}
	
	@Transactional
	public List<ChatForum> listAllChatsByForumId(int forumId) {
		return chatForumDAO.listAllChatsByForumId(forumId);

	}
	
	@Transactional
	public void deleteForumChat(int chatForumId) {
		chatForumDAO.deleteForumChat(chatForumId);
	}
}