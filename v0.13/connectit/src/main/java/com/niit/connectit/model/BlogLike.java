package com.niit.connectit.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BlogLike {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogLikeId;
	private int blogId;
	private int userId;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="userId",nullable=false,insertable=false,updatable=false)
	private NewUser user;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="blogId",nullable=false,insertable=false,updatable=false)
	private Blog blog;

	public int getBlogLikeId() {
		return blogLikeId;
	}

	public void setBlogLikeId(int blogLikeId) {
		this.blogLikeId = blogLikeId;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
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

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	
}
