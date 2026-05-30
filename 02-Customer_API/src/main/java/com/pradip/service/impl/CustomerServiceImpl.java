package com.pradip.service.impl;

import java.util.Optional;
import java.util.Random;

import com.pradip.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradip.dto.CustomerDTO;
import com.pradip.dto.ResetPwdDTO;
import com.pradip.entity.Customer;
import com.pradip.repository.CustomerRepo;
import com.pradip.repository.ShippingAddressRepo;
import com.pradip.service.CustomerService;
import com.pradip.service.EmailService;

import lombok.AllArgsConstructor;

import static com.pradip.mapper.CustomerMapper.mapper;

@Service
//@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ShippingAddressRepo addressRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public CustomerDTO customerRegister(CustomerDTO customerDTO) {

		//copy data/properties from DTO obj to Entity Obj
		Customer customer = CustomerMapper.convertToEntity(customerDTO);
		customer.setPassword(generateRandomPwd(8));
		customer.setPwdUpdated("No");
		Customer savedCustomer = customerRepo.save(customer);
		
		if (savedCustomer.getCustomerId() != null) {
			String subject = "Pradip Chavan - Your Account created";
			String body = "<h2>Your temparory password is -"+customer.getPassword();

			boolean emailSent = emailService.sendEmail(subject, body, customerDTO.getEmail());
			if (emailSent) {
				return CustomerMapper.convertToDto(savedCustomer);
			}
		}

		return null;

	}

	@Override
	public CustomerDTO login(String email, String pwd) {
		
		Customer customer = customerRepo.findByEmailAndPassword(email, pwd);
		
		/*
		 * CustomerDTO customerDTO = new CustomerDTO();
		 * 
		 * BeanUtils.copyProperties(customer, customerDTO);
		 * return customerDTO;
		 */
		
		if (customer != null) {
			return CustomerMapper.convertToDto(customer);
		}
		
		return null;
	}

	@Override
	public boolean resetPwd(ResetPwdDTO resetPwdDto) {
		
		Customer customer = customerRepo.findByEmail(resetPwdDto.getEmail());
		
		if (customer != null) {
			customer.setPassword(resetPwdDto.getNewPassword());
			customer.setPwdUpdated("Yes");
			//update user record with new pwd and pwdUpdate flag
			customerRepo.save(customer);
			return true;
			
		}
		
		return false;
	}

	@Override
	public CustomerDTO getCustomerByEmail(String emailId) {

		Customer customer = customerRepo.findByEmail(emailId);
		if (customer != null) {
			return CustomerMapper.convertToDto(customer);
		}

		return null;
	}

	private String generateRandomPwd(int pwdLenth) {
		
		Random random = new Random();
		
		String str = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < pwdLenth; i++) {
			
			int randomNum = random.nextInt(str.length());
			char c = str.charAt(randomNum);
			
			builder.append(c);
		}
		
		return builder.toString();
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		
		Optional<Customer> customerOptional = customerRepo.findById(customerDTO.getCustomerId());
		
		if (customerOptional.isPresent()) {
			
			//Customer customer = customerOptional.get();
			//customer.setEmail(customerDTO.getEmail());

			//copy data/properties from DTO obj to Entity Obj
			//Customer customer = mapper.map(customerDTO, Customer.class);

			Customer customer = CustomerMapper.convertToEntity(customerDTO);

			Customer savedCustomer = customerRepo.save(customer);

			return CustomerMapper.convertToDto(savedCustomer);

		}
		
		return null;
	}

}
