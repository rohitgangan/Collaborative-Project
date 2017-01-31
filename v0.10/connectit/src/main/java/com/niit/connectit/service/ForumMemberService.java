package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.ForumMemberDAOImpl;
import com.niit.connectit.model.ForumMember;

@Service
public class ForumMemberService {

	@Autowired
	private ForumMemberDAOImpl forumMemberDAO;
	
	@Transactional
	public boolean addForumMember(ForumMember forumMember){
		forumMemberDAO.addForumMember(forumMember);
		return true;		
	}
	
	@Transactional
	public List<ForumMember> listAllMembersByForum(int forumId) {
		return forumMemberDAO.listAllMembersByForum(forumId);
	}
	
	@Transactional
	public void deleteForumMember(int forumMemberId) {
		forumMemberDAO.deleteForumMember(forumMemberId);
	}
	
}
