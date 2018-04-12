package com.springworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApplicationConfig extends SpringBootServletInitializer {

	public static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
	public static void main(String [] args) {
		logger.info("Executing main....");
		SpringApplication.run(ApplicationConfig.class, args);
	}
	
  @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      logger.info("executing configure method..."); 
	  return application.sources(ApplicationConfig.class);
    }

}
