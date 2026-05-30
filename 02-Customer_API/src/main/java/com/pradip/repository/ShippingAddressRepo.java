package com.pradip.repository;

import java.util.List;

import com.pradip.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepo extends JpaRepository<ShippingAddress, Integer> {

	//select * from shipping_addr where customer_id=? and delete_sw=?;//get active address
	public List<ShippingAddress> findByCustomerCustomerIdAndDeleteSw(Integer customerId, String deletSw);
	
}
