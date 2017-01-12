package com.niit.connectit.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Forum {
	
	@Id
	private int forumId;
	private String forumName;
	private String forumContent;
	private int userId;
	private Date forumCreatedDate;
	@Transient
	private MultipartFile image;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="userId",nullable=false,insertable=false,updatable=false)
	private NewUser user;

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getForumContent() {
		return forumContent;
	}

	public void setForumContent(String forumContent) {
		this.forumContent = forumContent;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getForumCreatedDate() {
		return forumCreatedDate;
	}

	public void setForumCreatedDate(Date forumCreatedDate) {
		this.forumCreatedDate = forumCreatedDate;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public NewUser getUser() {
		return user;
	}

	public void setUser(NewUser user) {
		this.user = user;
	}
	
	
	
}
