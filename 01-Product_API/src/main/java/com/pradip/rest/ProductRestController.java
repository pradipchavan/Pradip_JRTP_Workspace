package com.pradip.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pradip.dto.ApiResponse;
import com.pradip.dto.ProductCategoryDTO;
import com.pradip.dto.ProductDTO;
import com.pradip.exception.NoProductsFoundException;
import com.pradip.service.ProductCategoryService;
import com.pradip.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
//@AllArgsConstructor
@Slf4j
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCategoryService categoryService;
	
	//get product category
	@GetMapping("/categories")
	public ResponseEntity<ApiResponse<List<ProductCategoryDTO>>> getProductCategroy() {
		
		log.debug("Method execution started");
		
		List<ProductCategoryDTO> allProductCategory = categoryService.getAllProductCategory();
		
		ApiResponse<List<ProductCategoryDTO>> apiResponse = new ApiResponse<>();
		
		if (allProductCategory.isEmpty()) {
			
			log.warn("No category available");
			
			apiResponse.setStatus(200);
			apiResponse.setMessage("No category available");
			
			log.debug("Method execution complted");
			
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			
		} else {
			apiResponse.setStatus(200);
			apiResponse.setMessage("fetched all categories");
			apiResponse.setData(allProductCategory);
			
			log.debug("Method execution complted");
			
			log.info("Fetched Product Categories successfully");
			
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		
		
	}
	
	//get product based on product category
	@GetMapping("/products/{categoryId}")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductByCategory(@PathVariable Integer categoryId) {
		
		log.debug("Method execution started");
		
		List<ProductDTO> productByCategory = productService.getProductsByCategory(categoryId);
		
		ApiResponse<List<ProductDTO>> apiResponse = new ApiResponse<>();
		
		
		
		if (productByCategory.isEmpty()) {
			
			/*
			 * log.warn("No product found wih given Category Id");
			 * apiResponse.setStatus(200);
			 * apiResponse.setMessage("No product found wih given Category Id");
			 * log.debug("Method execution completed"); return new
			 * ResponseEntity<>(apiResponse, HttpStatus.OK);
			 */
			
			throw new NoProductsFoundException("No products found with given Category Id");
			
		} else {
			apiResponse.setStatus(200);
			apiResponse.setMessage("Fetched all products based on category id");
			apiResponse.setData(productByCategory);
			
			log.debug("Method execution completed");
			log.info("Fetched all Product based on category id succefully");
			
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			
		}
		
		
		
		
	}
	
	//get product based on product name (Search functionality)
	@GetMapping("/productsByName/{name}")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductByName(@PathVariable String name) {
		
		log.debug("Method execution started");
		
		List<ProductDTO> productByName = productService.getProductsByProductName(name);
		
		ApiResponse<List<ProductDTO>> apiResponse = new ApiResponse<>();
		
		if (productByName.isEmpty()) {
			/*
			 * apiResponse.setStatus(500);
			 * apiResponse.setMessage("Failed to fetch product"); 
			 * return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			 */
			throw new NoProductsFoundException("No products found with given product name");
		} else {
			apiResponse.setStatus(200);
			apiResponse.setMessage("Fetched all products based on product name");
			apiResponse.setData(productByName);
			
			log.debug("Method execution completed");
			log.info("Fetched all products based on product name");
			
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		
	}
	
	//get product by product id (for Product master view)
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<ProductDTO>> getProductByProductId(@PathVariable Integer productId) {
		
		log.debug("Method execution started");
		
		ProductDTO productDto = productService.getProductByProductId(productId);
		
		ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
		
		
		if (productDto != null) {
			
			log.info("Product Fetched with product Id successfully");
			
			apiResponse.setStatus(200);
			apiResponse.setMessage("Product Fetched with product Id");
			apiResponse.setData(productDto);
			
			log.debug("Method execution started");
			
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} else {
			/*
			 * log.warn("No product available");
			 * 
			 * apiResponse.setStatus(200); apiResponse.setMessage("No product available");
			 * 
			 * log.debug("Method execution started");
			 * 
			 * return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			 */
			
			throw new NoProductsFoundException("No product available");
		}
		
		
		
	}
	
}
