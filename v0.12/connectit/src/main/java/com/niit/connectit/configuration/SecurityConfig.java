package com.niit.connectit.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select username, password, enabled from NewUser where username=?")
        .authoritiesByUsernameQuery("select username, role from NewUser where username=?");
            
    }
	
    @Bean(name="authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
		
	       return super.authenticationManagerBean();
	   }
	
	
	
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
   
      http.authorizeRequests()
      .antMatchers("/admin").access("hasRole('ADMIN')")
		.and()
		  .formLogin().loginPage("http://localhost:8082/connectit-frontend/#/").loginProcessingUrl("/perform_login").failureUrl("/login?error=true")
		  .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("http://localhost:8082/connectit-frontend/#/blog")
		.and()
		  .logout().logoutSuccessUrl("/perform_logout")
		.and()
		  .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf().disable();
    }   
}
