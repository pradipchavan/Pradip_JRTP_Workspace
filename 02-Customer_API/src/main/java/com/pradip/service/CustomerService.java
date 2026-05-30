package com.pradip.service;

import java.util.List;

import com.pradip.dto.CustomerDTO;
import com.pradip.dto.ResetPwdDTO;
import com.pradip.dto.ShippingAddressDTO;

public interface CustomerService {
	
	public CustomerDTO customerRegister(CustomerDTO customerDTO);
	
	public CustomerDTO login(String email, String pwd);
	
	public boolean resetPwd(ResetPwdDTO resetPwdDto);
	
	public CustomerDTO updateCustomer(CustomerDTO customerDTO);

	public CustomerDTO getCustomerByEmail(String emailId);
	
	
	
}
