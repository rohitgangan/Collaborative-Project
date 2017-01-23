package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.ForumMemberDAO;
import com.niit.connectit.model.ForumMember;

@Repository
public class ForumMemberDAOImpl implements ForumMemberDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean addForumMember(ForumMember forumMember) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(forumMember);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<ForumMember> listAllMembersByForum(int forumId) {
		// TODO Auto-generated method stub
		String hql = "from ForumMember where forumId ="+forumId;
		List<ForumMember> memberList = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return memberList;
	}

	public void deleteForumMember(int forumMemberId) {
		// TODO Auto-generated method stub
		ForumMember memberDelete = new ForumMember();
		memberDelete.setForumMemberId(forumMemberId);
		this.sessionFactory.getCurrentSession().delete(memberDelete);
	}
	
	
}
