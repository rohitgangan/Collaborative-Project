package com.niit.connectit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventId;
	private String eventName;
	@Lob
	private String eventDetails;
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}
	
	
}
