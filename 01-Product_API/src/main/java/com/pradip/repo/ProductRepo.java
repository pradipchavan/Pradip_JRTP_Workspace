package com.pradip.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradip.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	
	
	/*select * from product where category = ?*/
	public List<Product> findByCategoryCategoryId(Integer categoryId);
	
	/* select * from product where name like %name% */
	public List<Product> findByNameContainingIgnoreCase(String name);
	
	
}
