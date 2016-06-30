package com.halal.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com.halal.web"})
public class App extends SpringBootServletInitializer{

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
		return applicationBuilder.sources(App.class);
	}
	
	public static void main(String[] args) {
		LOGGER.info("Starting the app...");
		SpringApplication.run(App.class, args);

	}

}
