package com.niit.connectit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.connectit.model.Event;
import com.niit.connectit.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;
	
	@RequestMapping(value="/event", method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Event event){
		eventService.save(event);
		return new ResponseEntity<Void> (HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/event", method=RequestMethod.GET)
	public ResponseEntity<List<Event>> listEvents(){
		List<Event> list = eventService.listEvents();
		return new ResponseEntity<List<Event>>(list,HttpStatus.OK);
	}
	
}
