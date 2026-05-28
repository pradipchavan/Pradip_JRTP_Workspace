package com.pradip.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMsgService {
	
	@Autowired
	private MsgService msgService;
	
	@Test
	public void testWelcomeMsg() {
		//calling target method
		String welcomeMsg = msgService.welcomeMsg();
		
		//validate the method result- check null value
		Assertions.assertNotNull(welcomeMsg);
		
	}
	
	@Test
	public void testGreetMsg() {
		//calling target method
		String greetMsg = msgService.greetMsg();
		
		//validate target method result
		Assertions.assertNotNull(greetMsg);
		
	}
	
	@Test
	public void testWish1() {
		//calling target method
		String wishResult = msgService.wish("Raju");
		Assertions.assertEquals("Hey, Raju", wishResult);
	}
	
	@Test
	public void testWish2() {
		//calling target method
		String wishResult = msgService.wish("Rani");
		Assertions.assertEquals("Hey, Rani", wishResult);
	}
	
	@Test
	public void testWish3() {
		//calling target method
		String wishResult = msgService.wish("Pradip");
		Assertions.assertNull(wishResult);
	}
	
}
