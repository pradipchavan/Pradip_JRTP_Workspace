package com.pradip.service;

import org.springframework.stereotype.Service;

@Service
public class MsgService {
	
	public String welcomeMsg() {
		String welocmeMsg =  "Welcome to Pradip....!!!!";
		return welocmeMsg;
	}
	
	public String greetMsg() {
		String greetMsg =  "Good morning...!!!";
		return greetMsg;
	}
	
	public String wish(String name) {
		if (name.equals("Raju")) {
			return "Hey, Raju";
		} else if (name.equals("Rani")) {
			return "Hey, Rani";
		} else {
			return null;
		}
		
	}
}
