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

	@SuppressWarnings("unchecked")
	public Event getById(int eventId) {
		// TODO Auto-generated method stub
		String hql = "from Event where eventId = "+eventId;
		List<Event> list = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}else{
			return null;
		}
	}

	public void updateEvent(Event event) {
		// TODO Auto-generated method stub
		Event e = getById(event.getEventId());
		e.setEventName(event.getEventName());
		e.setEventDetails(event.getEventDetails());
		sessionFactory.getCurrentSession().update(e);
	}

	public void delete(int eventId) {
		// TODO Auto-generated method stub
		Event event = new Event();
		event.setEventId(eventId);
		sessionFactory.getCurrentSession().delete(event);
	}

}
