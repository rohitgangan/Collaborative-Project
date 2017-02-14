package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.Job;
import com.niit.connectit.model.JobApplication;

public interface JobDAO {

	public boolean save(Job job);
	public boolean apply(JobApplication jobApplication);
	public List<Job> getAllJobs();
	public List<JobApplication> getMyJobs(int userId);
	public List<JobApplication> getAllJobApplication();
	public Job getJobById(int jobId);
	public void update(int userId, int jobId);
	public void reject(int userId,int jobId);
	public boolean isExists(int userId,int jobId);
}
