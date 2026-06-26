package com.pradip.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradip.dto.ProductDTO;
import com.pradip.entities.Product;
import com.pradip.mapper.ProductMapper;
import com.pradip.repo.ProductRepo;
import com.pradip.service.ProductService;

import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	
	@Override
	public List<ProductDTO> getProductsByCategory(Integer categoryId) {
		
		//Approach-1
		/***
		ProductCategory category = new ProductCategory();
		category.setCategoryId(categoryId);
		
		Product product = new Product();
		product.setCategory(category);
		
		List<Product> all = productRepo.findAll(Example.of(product));
		
		***/
		/*
		List<Product> productList = productRepo.findByCategoryCategoryId(categoryId);
		return productList.stream()
				   .map(product -> mapper.map(product, ProductDTO.class))
				   .collect(Collectors.toList());
		*/
		return productRepo.findByCategoryCategoryId(categoryId)
					.stream()
					.map(ProductMapper::convertToDto)
					.toList();
	}

	@Override
	public List<ProductDTO> getProductsByProductName(String productName) {
		return productRepo.findByNameContainingIgnoreCase(productName)
				.stream()
				.map(ProductMapper::convertToDto)
				.toList();
		
	}

	@Override
	public ProductDTO getProductByProductId(Integer productId) {
		
		/* approach -1
		//Product product = productRepo.findById(productId).orElseThrow();
		
		//Optional<Product> product = productRepo.findById(productId);
		//if (product.isPresent()) { Product product2 = product.get(); }
		
		ProductDTO productDto = new ProductDTO();
		
		BeanUtils.copyProperties(product, productDto);
		
		return productDto;
		*/
		
		return productRepo.findById(productId)
					.map(ProductMapper::convertToDto)
					.orElse(null);
	}

}
