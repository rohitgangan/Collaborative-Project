package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.JobDAO;
import com.niit.connectit.model.Job;
import com.niit.connectit.model.JobApplication;

@Repository
public class JobDAOImpl implements JobDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean save(Job job) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(job);
		return false;
	}

	public boolean apply(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(jobApplication);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		String hql="from Job where Status='N'";
		List<Job> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<JobApplication> getMyJobs(int userId) {
		// TODO Auto-generated method stub
		String hql="from JobApplication where Status='A' and userId="+userId;
		List<JobApplication> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<JobApplication> getAllJobApplication() {
		// TODO Auto-generated method stub
		String hql="from JobApplication where Status='N'";
		List<JobApplication> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public Job getJobById(int jobId) {
		// TODO Auto-generated method stub
		String hql = "from Job where jobId="+jobId;
		List<Job> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}else{
		return null;
		}
	}

	public void update(int userId, int jobId) {
		// TODO Auto-generated method stub
		String hql="update JobApplication set Status='A' where userId=" +userId+"and jobId="+jobId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public void reject(int userId, int jobId) {
		// TODO Auto-generated method stub
		String hql="update JobApplication set Status='R' where userId=" +userId+"and jobId="+jobId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public boolean isExists(int userId, int jobId) {
		// TODO Auto-generated method stub
		String hql="from JobApplication where userId=" +userId+"and jobId="+jobId;
		List<JobApplication> jobs = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return jobs.size()>0 ? true : false;
	}
	

}
