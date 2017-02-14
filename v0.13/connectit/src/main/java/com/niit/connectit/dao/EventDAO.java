package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.Event;
import com.niit.connectit.model.EventMember;

public interface EventDAO {
	public boolean save(Event event);
	public boolean add(EventMember member);
	public List<Event> listEvents();
	public List<EventMember> listMembers(int eventId);
	public Event getById(int eventId);
	public void updateEvent(Event event);
	public void delete(int eventId);
	public boolean memberExist(int userId, int eventId);
}
