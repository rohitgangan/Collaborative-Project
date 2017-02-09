package com.niit.connectit.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JobApplication {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobApplicationId;
	private int userId;
	private int jobId;
	private Date jobApplied;
	private char status;
	private String remark;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="userId",nullable=false,insertable=false,updatable=false)
	private NewUser user;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="jobId",nullable=false,insertable=false,updatable=false)
	private Job job;

	public int getJobApplicationId() {
		return jobApplicationId;
	}

	public void setJobApplicationId(int jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Date getJobApplied() {
		return jobApplied;
	}

	public void setJobApplied(Date jobApplied) {
		this.jobApplied = jobApplied;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public NewUser getUser() {
		return user;
	}

	public void setUser(NewUser user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	
}
