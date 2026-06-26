package com.pradip.dto;

import lombok.Data;

@Data
public class ProductDTO {
	
	private Integer productId;
	
	private String name;
	
	private String desc;
	
	private String title;
	
    private Double unitPrice;
    
    private String imageUrl;
    
    private boolean active;
    
    private int unitsStock;
	
	private Integer categoryId;
}
