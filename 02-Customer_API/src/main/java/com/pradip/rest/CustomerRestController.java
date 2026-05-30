package com.pradip.rest;

import com.pradip.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pradip.dto.CustomerDTO;
import com.pradip.dto.ResetPwdDTO;
import com.pradip.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
//@AllArgsConstructor
@Slf4j
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer")
	public ResponseEntity<ApiResponse<CustomerDTO>> customerRegister(@RequestBody CustomerDTO customerDTO) {

		ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();

		CustomerDTO savedCustomer = customerService.customerRegister(customerDTO);

		if (savedCustomer != null) {
			apiResponse.setStatus(201);
			apiResponse.setMessage("Customer registered");
			apiResponse.setData(savedCustomer);
			return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
			
		} else {
			apiResponse.setStatus(500);
			apiResponse.setMessage("Customer registration failed");
			apiResponse.setData(null);
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<CustomerDTO>> login(@RequestBody CustomerDTO customerDTO) {

		ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();

		CustomerDTO customer = customerService.login(customerDTO.getEmail(), customerDTO.getPassword());

		if (customer != null) {
			apiResponse.setStatus(200);
			apiResponse.setMessage("Login success");
			apiResponse.setData(customer);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setStatus(401);
			apiResponse.setMessage("Invalid Credentials");
			apiResponse.setData(null);
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/reset-pwd")
	public ResponseEntity<ApiResponse<String>> resetPwd(@RequestBody ResetPwdDTO resetPwdDto) {

		ApiResponse<String> apiResponse = new ApiResponse<>();

		boolean resetPwd = customerService.resetPwd(resetPwdDto);

		if (resetPwd) {
			apiResponse.setStatus(200);
			apiResponse.setMessage("Password updated");
			apiResponse.setData("SUCCESS");
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setStatus(500);
			apiResponse.setMessage("Reset password failed");
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customer/{email}")
	public ResponseEntity<ApiResponse<CustomerDTO>> getCustomer(@PathVariable  String email) {

		ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();

		CustomerDTO customer = customerService.getCustomerByEmail(email);

		if (customer != null) {
			apiResponse.setStatus(200);
			apiResponse.setMessage("Customer Fetched");
			apiResponse.setData(customer);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setStatus(500);
			apiResponse.setMessage("Failed to fetch Customer");
			apiResponse.setData(null);
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customer")
	public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(@RequestBody CustomerDTO customerDTO) {

		ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();

		CustomerDTO savedCustomer = customerService.updateCustomer(customerDTO);

		if (savedCustomer != null) {
			apiResponse.setStatus(201);
			apiResponse.setMessage("Customer updated");
			apiResponse.setData(savedCustomer);
			return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

		} else {
			apiResponse.setStatus(500);
			apiResponse.setMessage("Failed to update Customer");
			apiResponse.setData(null);
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}
	
}
