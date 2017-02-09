package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.Event;

public interface EventDAO {
	public boolean save(Event event);
	public List<Event> listEvents();
	public Event getById(int eventId);
	public void updateEvent(Event event);
	public void delete(int eventId);
}
