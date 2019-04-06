package com.cloud9.onboard.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cloud9.onboard.camunda.dao.ProcessDao;
import com.cloud9.onboard.camunda.dao.ProcessDaoRestImpl;
import com.cloud9.onboard.camunda.dao.TaskDao;
import com.cloud9.onboard.camunda.dao.TaskDaoRestImpl;
import com.cloud9.onboard.camunda.service.ProcessService;
import com.cloud9.onboard.camunda.service.ProcessServiceImpl;
import com.cloud9.onboard.camunda.service.TaskService;
import com.cloud9.onboard.camunda.service.TaskServiceImpl;


@Configuration
public class Config {

	  @Bean 
	    public RestTemplate restTemplate() {
	    	RestTemplate template = new RestTemplate();
	    	List<HttpMessageConverter<?>> messageConverters = 
					new ArrayList<HttpMessageConverter<?>> ();
	    	messageConverters.add(new MappingJackson2HttpMessageConverter());
	    	messageConverters.add(new org.springframework.http.converter.FormHttpMessageConverter());
	    	messageConverters.add(new org.springframework.http.converter.ByteArrayHttpMessageConverter());
	    	messageConverters.add(new org.springframework.http.converter.xml.SourceHttpMessageConverter());
	    	messageConverters.add(new org.springframework.http.converter.StringHttpMessageConverter());
	    	template.setMessageConverters(messageConverters);
	    	return template;
	    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5000000);
        return multipartResolver;
    }
    
    
    @Bean
    public ProcessService processService(){
    	return new ProcessServiceImpl();
    }
    
    @Bean ProcessDao processDao(){
    	return new ProcessDaoRestImpl();
    }
    
    @Bean
    public TaskService taskService(){
    	return new TaskServiceImpl();
    }
    
    @Bean TaskDao taskDao(){
    	return new TaskDaoRestImpl();
    }
    
    
    
}