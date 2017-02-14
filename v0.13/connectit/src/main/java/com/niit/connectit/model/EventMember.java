package com.niit.connectit.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EventMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventMemberId;
	private int eventId;
	private int userId;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="userId",nullable=false,insertable=false,updatable=false)
	private NewUser user;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="eventId",nullable=false,insertable=false,updatable=false)
	private Event event;

	public int getEventMemberId() {
		return eventMemberId;
	}

	public void setEventMemberId(int eventMemberId) {
		this.eventMemberId = eventMemberId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public NewUser getUser() {
		return user;
	}

	public void setUser(NewUser user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
