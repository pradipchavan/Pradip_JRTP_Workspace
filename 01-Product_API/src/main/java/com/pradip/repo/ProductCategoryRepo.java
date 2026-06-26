package com.pradip.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradip.entities.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer> {
	
	public List<ProductCategory> findAll();
}
