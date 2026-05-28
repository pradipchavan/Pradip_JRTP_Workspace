package com.pradip.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradip.service.MsgService;

@RestController
public class MsgServiceController {
	
	@Autowired
	private MsgService msgService;

	@GetMapping("/welcome")//method mapped with get request using @GetMapping with url pattern welcome
	public String welcomeMsg() {
		String welcomeMsg = msgService.welcomeMsg();
		String formatedMsg = welcomeMsg.toUpperCase();
		return formatedMsg;
	}
	
}
