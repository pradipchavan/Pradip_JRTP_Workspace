package com.pradip.mapper;

import org.modelmapper.ModelMapper;

import com.pradip.dto.ProductCategoryDTO;
import com.pradip.entities.ProductCategory;

public class ProductCategoryMapper {

	public static final ModelMapper mapper = new ModelMapper();
	
	public static ProductCategory convertToEntity(ProductCategoryDTO categoryDTO) {
		return mapper.map(categoryDTO, ProductCategory.class);
	}
	
	public static ProductCategoryDTO convertToDto(ProductCategory entity) {
		return mapper.map(entity, ProductCategoryDTO.class);
	}
	
}
