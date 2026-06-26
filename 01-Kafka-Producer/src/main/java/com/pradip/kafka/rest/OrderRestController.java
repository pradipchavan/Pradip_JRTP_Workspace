package com.pradip.kafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradip.kafka.dto.Order;
import com.pradip.kafka.service.OrderService;

@RestController
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order")
	public String createOrder(@RequestBody Order order) {
		
		orderService.publishMessage(order);
		
		System.out.println("**** Messaged Published to Kafka topic ****");
		
		return "Message published to Kafka Topic";
		
	}
}
