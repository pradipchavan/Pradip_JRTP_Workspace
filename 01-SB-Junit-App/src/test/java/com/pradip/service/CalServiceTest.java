package com.pradip.service;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalServiceTest {

	// @Test
	public void testPrivateMethod() throws Exception {

		CalService cal = new CalService();

		// using Reflection API we can access private Method, field and Constructor
		// etc...

		// get method name "sum" with int, int from CalService class
		Method method = CalService.class.getDeclaredMethod("sum", int.class, int.class);

		// set private method as accessible true
		method.setAccessible(true);

		// call/invoke the method dynamically
		int result = (int) method.invoke(cal, 20, 30);

		Assertions.assertEquals(50, result);
	}

	@Test
	public void testIsEvenTrue() {
		boolean isEven = CalService.isEven(10);
		Assertions.assertTrue(isEven);

	}

	@Test
	public void testIsEvenFalse() {
		boolean isEven = CalService.isEven(11);
		Assertions.assertFalse(isEven);

	}

	
	  @ParameterizedTest//test parameterized method
	  @ValueSource(ints = { 2, 4, 6, 8, 10 })//take multiple values as inputs to method
	  public void testEven(int num) { 
		  boolean isEven = CalService.isEven(num);
		  Assertions.assertTrue(isEven);
	  
	  }
	 
}
