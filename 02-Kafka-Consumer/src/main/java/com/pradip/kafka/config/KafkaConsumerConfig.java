package com.pradip.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import com.pradip.kafka.dto.Order;

@Configuration
public class KafkaConsumerConfig {
	
	@Value("${bootstrap.server.url}")
	private String bootstrapServerUrl;
	
	
	@Bean
	public ConsumerFactory<String, Order> consumerFactory() {
		
		Map<String, Object> configProps = new HashMap<>();
		
		configProps.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerUrl);
		//Key-Topic name
		configProps.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		//Value-Message
		configProps.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
		
		
		return new DefaultKafkaConsumerFactory<>(configProps);
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListner() {
		
		ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<>();
		
		factory.setConsumerFactory(consumerFactory());
		
		return factory;
	}

}
