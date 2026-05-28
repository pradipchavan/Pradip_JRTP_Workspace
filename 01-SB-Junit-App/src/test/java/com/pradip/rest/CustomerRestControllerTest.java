package com.pradip.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pradip.service.CustomerService;

@WebMvcTest(controllers = CustomerRestController.class)
public class CustomerRestControllerTest {
	
	//create mock object
	@MockitoBean
	private CustomerService customerService;
	
	@Autowired
	private MockMvc mockMvc;//send https request to controller method
	
	public void saveCustomerTest() {
		
	}
	
	
	

}
