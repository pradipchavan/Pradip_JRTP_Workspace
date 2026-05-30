package com.pradip.repository;

import com.pradip.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	public Customer findByEmailAndPassword(String email, String pwd);
	
	public Customer findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
}
