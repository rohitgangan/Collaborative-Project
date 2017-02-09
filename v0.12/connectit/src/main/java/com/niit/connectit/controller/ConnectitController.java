package com.niit.connectit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConnectitController {
	
	@RequestMapping("/")
	public String home(){
		
		return "index";
		
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
    public ModelAndView visitAdmin() {
        ModelAndView model = new ModelAndView("admin");
        model.addObject("title", "Admministrator Control Panel");
        model.addObject("message", "This page demonstrates how to use Spring security.");
         
        return model;
    }
	
	@RequestMapping(value="/perform_logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null)
		{
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		
			
		}
		
		return "redirect:/";			
	}
	
	/*@RequestMapping(value="/newBlog", method = RequestMethod.GET)
	public String Blog(){
		return "blog";
	}*/
	
}
