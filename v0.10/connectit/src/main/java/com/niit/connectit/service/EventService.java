package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.EventDAOImpl;
import com.niit.connectit.model.Event;

@Service
public class EventService {
	
	@Autowired
	private EventDAOImpl eventDAO;
	
	@Transactional
	public synchronized boolean save(Event event) {
		eventDAO.save(event);
		return true;
	}
	
	@Transactional
	public List<Event> listEvents() {
		return eventDAO.listEvents();
	}
}
