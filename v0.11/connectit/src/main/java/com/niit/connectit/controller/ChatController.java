package com.niit.connectit.controller;



import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.niit.connectit.model.Message;
import com.niit.connectit.model.OutputMessage;

@Controller
public class ChatController {
	
	@MessageMapping("/chat")
	  @SendTo("/topic/message")
	  public OutputMessage sendMessage(Message message) {
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String username = authentication.getName();
	      System.out.println("message:" +username);*/
		System.out.println("message:" +message.getMessage());
		return new OutputMessage(message, new Date());
	  }
}
