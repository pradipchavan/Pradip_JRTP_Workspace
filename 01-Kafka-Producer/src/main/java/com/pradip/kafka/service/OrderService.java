package com.pradip.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pradip.kafka.dto.Order;

@Service
public class OrderService {
	
	@Value("${kafka.topic.name}")
	private String topic;

	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public String publishMessage(Order order) {
		
		//Publish message to Kafka Tipic
		kafkaTemplate.send(topic, order);
		
		System.out.println("**** Messaged Published to Kafka topic ****");
		
		return "Message Published";
	}
}
