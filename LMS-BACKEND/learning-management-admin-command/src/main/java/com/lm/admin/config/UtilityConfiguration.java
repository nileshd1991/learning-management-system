package com.lm.admin.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfiguration {
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
