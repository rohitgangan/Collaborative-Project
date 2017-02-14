package com.niit.connectit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.connectit.model.Event;
import com.niit.connectit.model.EventMember;
import com.niit.connectit.service.EventService;
import com.niit.connectit.service.UserService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/event", method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Event event){
		eventService.save(event);
		return new ResponseEntity<Void> (HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/eventMember/{eventId}", method=RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody EventMember member, @PathVariable("eventId") Integer eventId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      int userId = userService.getByUsername(username).getUserId();
	      member.setEventId(eventId);
	      member.setUserId(userId);
	     boolean flag = eventService.add(member);
	     if(flag==false){
	    	 return new ResponseEntity<Void>(HttpStatus.CONFLICT); 
	     }
		return new ResponseEntity<Void> (HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/eventMember/{eventId}", method=RequestMethod.GET)
	public ResponseEntity<List<EventMember>> listMembers(@PathVariable("eventId") Integer eventId){
		List<EventMember> list = eventService.listMembers(eventId);
		return new ResponseEntity<List<EventMember>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/event", method=RequestMethod.GET)
	public ResponseEntity<List<Event>> listEvents(){
		List<Event> list = eventService.listEvents();
		return new ResponseEntity<List<Event>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value= "/event/{eventId}", method = RequestMethod.GET)
	public ResponseEntity<Event> getBlogById(@PathVariable("eventId") Integer eventId,HttpSession session){
		
		Event event = eventService.getById(eventId);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/event/{eventId}", method = RequestMethod.PUT )
	public ResponseEntity<Event> update(@RequestBody Event event) {
		
		eventService.updateEvent(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	@RequestMapping(value="/event/{eventId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable("eventId") Integer eventId) {
		eventService.delete(eventId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
