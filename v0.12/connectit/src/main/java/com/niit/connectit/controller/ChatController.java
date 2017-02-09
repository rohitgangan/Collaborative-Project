package com.niit.connectit.controller;



import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.niit.connectit.model.Message;
import com.niit.connectit.model.OutputMessage;

@Controller
public class ChatController {
	
	@Autowired
	public SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/chat.message")
	  @SendTo("/topic/message")
	  public OutputMessage sendMessage(Message message, OutputMessage omessage, Principal principal) {
	      omessage.setRecipientName("Everyone");
		System.out.println("message:" +message.getMessage());
		return new OutputMessage(message, new Date(), principal.getName());
	  }
	
	@MessageMapping("/chat.private.{username}")
	public void filterPrivateMessage(Message message, OutputMessage omessage,@DestinationVariable("username") String username,Principal principal){
		omessage.setUsername(principal.getName());
		omessage.setRecipientName(username);
		simpMessagingTemplate.convertAndSend("/user/"+ username + "/exchange/amq.direct/chat.message", omessage);
	}
	
	
}
