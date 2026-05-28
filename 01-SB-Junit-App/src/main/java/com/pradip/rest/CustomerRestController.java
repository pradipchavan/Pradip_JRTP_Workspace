package com.pradip.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradip.binding.Customer;
import com.pradip.service.CustomerService;

@RestController
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
		
		boolean isSaved = customerService.saveCustomer(customer);
		
		if (isSaved) {
			return new ResponseEntity<>("Cusomer saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Customer not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
