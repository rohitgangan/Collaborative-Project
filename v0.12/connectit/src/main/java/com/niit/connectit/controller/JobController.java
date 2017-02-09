package com.niit.connectit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.niit.connectit.model.Job;
import com.niit.connectit.model.JobApplication;
import com.niit.connectit.service.JobService;
import com.niit.connectit.service.UserService;

@RestController
public class JobController {

	@Autowired
	private UserService userService;
	@Autowired
	private JobService jobService;
	
	
	@RequestMapping(value="/createJob", method=RequestMethod.POST)
	public ResponseEntity<Void> Save(@RequestBody Job job, UriComponentsBuilder builder){
		Date date = new Date();
		job.setJobCreationDate(date);
		job.setStatus('N');
		
		boolean flag = jobService.save(job);
        if (flag == false) {
 	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/createJob/{jobId}").buildAndExpand(job.getJobId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	
	
	@RequestMapping(value="/apply/{jobId}", method=RequestMethod.POST)
	public ResponseEntity<Void> applyForJob(@RequestBody JobApplication jobApplication, @PathVariable("jobId") Integer jobId, UriComponentsBuilder builder){
		Date date = new Date();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    int userId = userService.getByUsername(username).getUserId();
		jobApplication.setJobApplied(date);
		jobApplication.setJobId(jobId);
		jobApplication.setUserId(userId);
		jobApplication.setStatus('N');
		boolean flag = jobService.apply(jobApplication);
        if (flag == false) {
 	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
          HttpHeaders headers = new HttpHeaders();
          headers.setLocation(builder.path("/apply/{jobId}").buildAndExpand(jobApplication.getJobId()).toUri());
          return new ResponseEntity<Void>(headers, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/getAllJobs", method=RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllJob(){
		List<Job> list = jobService.getAllJobs();
		return new ResponseEntity<List<Job>> (list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllJobApplications", method=RequestMethod.GET)
	public ResponseEntity<List<JobApplication>> getAllJobApplication(){
		List<JobApplication> list = jobService.getAllJobApplication();
		return new ResponseEntity<List<JobApplication>> (list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getMyJobs", method=RequestMethod.GET)
	public ResponseEntity<List<JobApplication>> getMyjobs(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	   int userId = userService.getByUsername(username).getUserId();
	    List<JobApplication> list = jobService.getMyJobs(userId);
	    return new ResponseEntity<List<JobApplication>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/job/{jobId}", method=RequestMethod.GET)
	public ResponseEntity<Job> getJobById(@PathVariable("jobId")Integer jobId){
		Job job = jobService.getJobById(jobId);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@RequestMapping(value="acceptJobApplication/{userId}/{jobId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("userId")Integer userId,@PathVariable("jobId") Integer jobId){
		jobService.update(userId, jobId);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="rejectJobApplication/{userId}/{jobId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> reject(@PathVariable("userId")Integer userId,@PathVariable("jobId") Integer jobId){
		jobService.reject(userId, jobId);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
}
