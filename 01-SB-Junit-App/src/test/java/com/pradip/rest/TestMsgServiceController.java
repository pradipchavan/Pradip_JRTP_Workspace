package com.pradip.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pradip.service.MsgService;

@WebMvcTest(controllers = MsgServiceController.class)
public class TestMsgServiceController {
	
	@MockitoBean
	private MsgService msgService;
	
	@Autowired
	private MockMvc mockMvc;//to send http request for rest controller method
	
	
	//@Test
	public void testWelcomeMsg1() throws Exception {
		//defining the mock msgService method behavior for unit test
		//Unit Testing for method return "dummy text";
		when(msgService.welcomeMsg()).thenReturn("Dummy Text");
		
		//Mock request builders to send get http request to /welcome
		//prepare get request
		MockHttpServletRequestBuilder httpRequest = MockMvcRequestBuilders.get("/welcome");
		
		//mock mvc send the http request and return the result
		MvcResult result = mockMvc.perform(httpRequest).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		Assertions.assertEquals(200, status);
	}
	
	//@Test
	public void testWelcomeMsg2() throws Exception {
		//defining the mock msgService method behavior for unit test
		//Unit Testing for method return null;
		when(msgService.welcomeMsg()).thenReturn(null);
		
		//Mock request builders to send get http request to /welcome
		MockHttpServletRequestBuilder httpRequest = MockMvcRequestBuilders.get("/welcome");
		
		//mock mvc send the http request and return the result
		MvcResult result = mockMvc.perform(httpRequest).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		Assertions.assertEquals(200, status);
	}
	
	//@Test
	public void testWelcomeMsg3() throws Exception {
		//defining the mock msgService method behavior for unit test
		//Unit Testing for method return exception;
		when(msgService.welcomeMsg()).thenThrow(RuntimeException.class);
		
		//Mock request builders to send get http request to /welcome
		MockHttpServletRequestBuilder httpRequest = MockMvcRequestBuilders.get("/welcome");
		
		//mock mvc send the http request and return the result
		MvcResult result = mockMvc.perform(httpRequest).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		Assertions.assertEquals(500, status);
	}
	
	@Test
	public void testWelcomeMsg4() throws Exception {
		//defining the mock msgService method behavior for unit test
		when(msgService.welcomeMsg()).thenThrow(RuntimeException.class);
		
		//Mock request builders to send get http request to /welcome
		MockHttpServletRequestBuilder httpRequest = MockMvcRequestBuilders.get("/welcome");
		
		//mock mvc send the http request and return the result
		mockMvc.perform(httpRequest)
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Exception Occured"));
			 	
		
	}
	
}
