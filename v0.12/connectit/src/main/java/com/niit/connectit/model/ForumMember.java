package com.niit.connectit.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ForumMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int forumMemberId;
	private int forumId;
	private int userId;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="userId",nullable=false,insertable=false,updatable=false)
	private NewUser user;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="forumId",nullable=false,insertable=false,updatable=false)
	private Forum forum;

	public int getForumMemberId() {
		return forumMemberId;
	}

	public void setForumMemberId(int forumMemberId) {
		this.forumMemberId = forumMemberId;
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
