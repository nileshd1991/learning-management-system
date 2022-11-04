package com.lm.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.lm.kafka.model.CourseEntity;

@EnableKafka
@Configuration
public class ListenerConfig {

	@Value("${kafka.server.url}")
	private String kafkaServerUrl;

	@Bean
	public ConsumerFactory<String, CourseEntity> courseConsumerFactory() {

		return new DefaultKafkaConsumerFactory<>(getKafkaConfig(), new StringDeserializer(),
				new JsonDeserializer<>(CourseEntity.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CourseEntity> courseKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CourseEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(courseConsumerFactory());
		return factory;
	}

	Map<String, Object> getKafkaConfig() {
		Map<String, Object> config = new HashMap<String, Object>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerUrl);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TYPE_MAPPINGS, "com.lm.admin.model.CourseEntity:com.lm.kafka.model.CourseEntity");

		return config;
	}
}
