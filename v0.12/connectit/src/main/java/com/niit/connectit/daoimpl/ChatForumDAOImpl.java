package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.ChatForumDAO;
import com.niit.connectit.model.ChatForum;

@Repository
public class ChatForumDAOImpl implements ChatForumDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean addChatForum(ChatForum chatForum) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(chatForum);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<ChatForum> listAllChatsByForumId(int forumId) {
		// TODO Auto-generated method stub
		String hql = "from ChatForum where forumId=" + forumId;
		List<ChatForum> list= sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return list;
	}

	public void deleteForumChat(int chatForumId) {
		// TODO Auto-generated method stub
		ChatForum deleteChatForum = new ChatForum();
		deleteChatForum.setChatForumId(chatForumId);
		sessionFactory.getCurrentSession().delete(deleteChatForum);
	}
}
