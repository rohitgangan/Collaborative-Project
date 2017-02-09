package com.niit.connectit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Job {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobId;
	private String title;
	private String qualification;
	private char status;
	@Lob
	private String description;
	private Date jobCreationDate;
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getJobCreationDate() {
		return jobCreationDate;
	}
	public void setJobCreationDate(Date jobCreationDate) {
		this.jobCreationDate = jobCreationDate;
	}
	
	
}
