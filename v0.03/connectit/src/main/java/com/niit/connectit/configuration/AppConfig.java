package com.niit.connectit.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.niit.connectit")
public class AppConfig extends WebMvcConfigurerAdapter{
	
	private MultipartResolver multipartResolver;
	private final Logger logger =  LoggerFactory.getLogger(MultipartResolver.class);

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	private void initMultipartResolver(ApplicationContext context)
	  {
	    try
	    {
	      this.multipartResolver = ((MultipartResolver)context.getBean("multipartResolver", MultipartResolver.class));
	      if (this.logger.isDebugEnabled()) {
	        this.logger.debug("Using MultipartResolver [" + this.multipartResolver + "]");
	      }
	    }
	    catch (NoSuchBeanDefinitionException ex)
	    {
	      this.multipartResolver = null;
	      if (this.logger.isDebugEnabled())
	        this.logger.debug("Unable to locate MultipartResolver with name 'multipartResolver': no multipart request handling provided");
	    }
	  }
}
