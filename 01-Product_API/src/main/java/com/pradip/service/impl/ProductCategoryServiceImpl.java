package com.pradip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradip.dto.ProductCategoryDTO;
import com.pradip.mapper.ProductCategoryMapper;
import com.pradip.repo.ProductCategoryRepo;
import com.pradip.service.ProductCategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepo categoryRepo;
	

	@Override
	public List<ProductCategoryDTO> getAllProductCategory() {
		
		/* approach -1
		List<ProductCategoryDTO> dtoList = new ArrayList<>();
		List<ProductCategory> list = categoryRepo.findAll();
		
		list.forEach(pc -> {
			ProductCategoryDTO categoryDTO = ProductCategoryMapper.convertToDto(pc);
			dtoList.add(categoryDTO);
		});
		
		//list.forEach(ProductCategoryMapper::convertToDto);

		return dtoList;
		*/
		
		
		/* Approach -1
		 List<ProductCategory> categoriesList = categoryRepo.findAll();
		 
		 return categoriesList.stream() .map(category -> mapper.map(category,
		 
		 ProductCategoryDTO.class)) .collect(Collectors.toList());
		 */
		
		return categoryRepo.findAll()
					.stream()
					//.map(category -> mapper.map(category,ProductCategoryDTO.class))
					//.map(c -> ProductCategoryMapper.convertToDto(c))
					.map(ProductCategoryMapper::convertToDto)
					//
					.toList();
		
		
	}

}
