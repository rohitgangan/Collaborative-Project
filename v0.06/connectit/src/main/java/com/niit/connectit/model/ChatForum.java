package com.niit.connectit.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChatForum {
	
	@Id
	private int chatForumId;
	private int forumId;
	private int userId;
	private String chatContent;
	private Date forumChatDate;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="userId",nullable=false,insertable=false,updatable=false)
	private NewUser user;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="forumId",nullable=false,insertable=false,updatable=false)
	private Forum forum;

	
	
	
	public Date getForumChatDate() {
		return forumChatDate;
	}

	public void setForumChatDate(Date forumChatDate) {
		this.forumChatDate = forumChatDate;
	}

	public int getChatForumId() {
		return chatForumId;
	}

	public void setChatForumId(int chatForumId) {
		this.chatForumId = chatForumId;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public NewUser getUser() {
		return user;
	}

	public void setUser(NewUser user) {
		this.user = user;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}
	
	
}
