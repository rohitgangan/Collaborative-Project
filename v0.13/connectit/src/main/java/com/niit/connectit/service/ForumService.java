package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.ForumDAOImpl;
import com.niit.connectit.model.Forum;

@Service
public class ForumService {
	
	@Autowired
	private ForumDAOImpl forumDAO;
	
	@Transactional
	public synchronized boolean addForum(Forum forum) {
			if(forumDAO.forumExist(forum.getForumName())){
				return false;
			}else{
				forumDAO.addForum(forum);
				return true;
			}
		}
	
	@Transactional
	public List<Forum> listForum() {
		return forumDAO.listForum();
		
		}
	
	@Transactional
	public Forum getForumById(int forumId) {
			return forumDAO.getForumById(forumId);
		}
	
	@Transactional
	public void deleteForum(int forumId) {
		forumDAO.deleteForum(forumId);
	}
	
	@Transactional
	public void updateForum(Forum forum) {
		forumDAO.updateForum(forum);
	}
	
	@Transactional
	public List<Forum> listUserForum(int userId) {
		return forumDAO.listUserForum(userId);
	}
}
