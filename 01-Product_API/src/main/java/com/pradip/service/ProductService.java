package com.pradip.service;

import java.util.List;

import com.pradip.dto.ProductDTO;

public interface ProductService {
	
	//find product based on category
	public List<ProductDTO> getProductsByCategory(Integer categoryId);
	
	public List<ProductDTO> getProductsByProductName(String productName);
	
	public ProductDTO getProductByProductId(Integer productId); 
	
}
