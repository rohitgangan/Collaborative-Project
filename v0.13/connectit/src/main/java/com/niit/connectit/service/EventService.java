package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.EventDAOImpl;
import com.niit.connectit.model.Event;
import com.niit.connectit.model.EventMember;

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
	
	@Transactional
	public Event getById(int eventId) {
		return eventDAO.getById(eventId);
	}
	
	@Transactional
	public void updateEvent(Event event) {
		eventDAO.updateEvent(event);
	}
	
	@Transactional
	public void delete(int eventId) {
		eventDAO.delete(eventId);
	}
	
	@Transactional
	public synchronized boolean add(EventMember member) {
		if(eventDAO.memberExist(member.getUserId(), member.getEventId())){
			return false;
		}else{
			eventDAO.add(member);
			return true;
		}
	}
	
	@Transactional
	public List<EventMember> listMembers(int eventId) {
		return eventDAO.listMembers(eventId);
	}
}
