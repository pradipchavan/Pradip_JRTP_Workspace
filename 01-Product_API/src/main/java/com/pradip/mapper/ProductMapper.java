package com.pradip.mapper;

import org.modelmapper.ModelMapper;

import com.pradip.dto.ProductDTO;
import com.pradip.entities.Product;

public class ProductMapper {

	public static final ModelMapper mapper = new ModelMapper();
	
	public static Product convertToEntity(ProductDTO productDTO) {
		return mapper.map(productDTO, Product.class);
	}
	
	public static ProductDTO convertToDto(Product entity) {
		return mapper.map(entity, ProductDTO.class);
	}
	
}
