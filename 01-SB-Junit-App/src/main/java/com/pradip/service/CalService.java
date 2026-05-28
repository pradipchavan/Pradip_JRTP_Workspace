package com.pradip.service;

import org.springframework.stereotype.Service;

@Service
public class CalService {
	
	private int sum(int a, int b) {
		return a + b;
	}
	
	
	public static boolean isEven(int num) {
		return num % 2 == 0;
	}
}
