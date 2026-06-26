package com.pradip.kafka.dto;

import lombok.Data;

@Data
public class Order {
	
	private Integer id;
	
	private Double price;
	
	private String email;

}
