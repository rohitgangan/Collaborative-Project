package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.JobDAOImpl;
import com.niit.connectit.model.Job;
import com.niit.connectit.model.JobApplication;

@Service
public class JobService {
	
	@Autowired
	private JobDAOImpl jobDAO;
	
	@Transactional
	public synchronized boolean save(Job job) {
		jobDAO.save(job);
		return true;
		
	}
	
	@Transactional
	public synchronized boolean apply(JobApplication jobApplication) {
		if(jobDAO.isExists(jobApplication.getUserId(), jobApplication.getJobId())){
			return false;
		}else{
		jobDAO.apply(jobApplication);
		return true;
		}
	}
	@Transactional
	public List<Job> getAllJobs() {
		return jobDAO.getAllJobs();
	}
	@Transactional
	public List<JobApplication> getMyJobs(int userId) {
		return jobDAO.getMyJobs(userId);
	}
	@Transactional
	public List<JobApplication> getAllJobApplication() {
		return jobDAO.getAllJobApplication();
	}
	@Transactional
	public Job getJobById(int jobId) {
		return jobDAO.getJobById(jobId);
	}
	@Transactional
	public void update(int userId, int jobId) {
		jobDAO.update(userId, jobId);
	}
	@Transactional
	public void reject(int userId, int jobId) {
		jobDAO.reject(userId, jobId);
	}
}
