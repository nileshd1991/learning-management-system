package com.lm.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LearningManagementAdminCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningManagementAdminCommandApplication.class, args);
	}

}
