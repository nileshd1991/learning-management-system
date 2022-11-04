package com.lm.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.lm.kafka*")
@ComponentScan(basePackages = "com.lm.kafka*")
@EntityScan(basePackages = "com.lm.kafka*")
public class LearningManagementKafkaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningManagementKafkaClientApplication.class, args);
	}

}
