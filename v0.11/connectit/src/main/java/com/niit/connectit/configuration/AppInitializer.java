package com.niit.connectit.configuration;

import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	  @Override
	  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
	    registration.setInitParameter("dispatchOptionsRequest", "true");
	    registration.setAsyncSupported(true);
	  }

	  @Override
	  protected Class< ?>[] getRootConfigClasses() {
	    return new Class< ?>[] { AppConfig.class, WebSocketConfig.class };
	  }

	  @Override
	  protected Class< ?>[] getServletConfigClasses() {
	    return new Class< ?>[] { AppConfig.class };
	  }

	  @Override
	  protected String[] getServletMappings() {
	    return new String[] { "/" };
	  }

	  
	}


 
