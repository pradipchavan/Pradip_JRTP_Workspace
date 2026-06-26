package com.pradip.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@KafkaListener(topics = "pradip_order_topic", groupId = "group_pradip_consumers")
	public void consumeMsg(String order) {
		System.out.println("**** Msg Recieved From Kafka ****");
		System.out.println(order);
	}

}
