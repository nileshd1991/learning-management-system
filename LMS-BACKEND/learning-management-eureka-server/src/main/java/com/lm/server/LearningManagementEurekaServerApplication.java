package com.lm.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaServer
public class LearningManagementEurekaServerApplication implements CommandLineRunner{
	
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(LearningManagementEurekaServerApplication.class, args);
		
		
	}
	
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println(env.getProperty("spring.data.mongodb.host"));
		
	}

}
