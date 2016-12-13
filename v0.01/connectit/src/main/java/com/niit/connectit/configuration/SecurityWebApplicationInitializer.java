package com.niit.connectit.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	 
    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
 
}