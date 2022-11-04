package com.lm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.lm.*")
@ComponentScan(basePackages = "com.lm.*")
@EntityScan(basePackages = "com.lm.*")
@CrossOrigin(origins = "*")
@EnableEurekaClient
public class LearningManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningManagementServiceApplication.class, args);
	}

}
