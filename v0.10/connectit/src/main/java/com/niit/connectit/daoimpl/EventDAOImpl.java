package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.EventDAO;
import com.niit.connectit.model.Event;

@Repository
public class EventDAOImpl implements EventDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean save(Event event) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(event);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Event> listEvents() {
		// TODO Auto-generated method stub
		List<Event> list = sessionFactory.getCurrentSession().createQuery("from Event").getResultList();
		return list;
	}

}
