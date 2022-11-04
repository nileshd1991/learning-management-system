package com.lm.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfiguration {
	
	@Bean
	public ModelMapper getModelMapperBean() {
		return new ModelMapper();
	}
}
