package com.pradip.dto;

import lombok.Data;

@Data
public class ShippingAddressDTO {
	
	private Integer addrId;
	
	private String houseNo;

    private String street;

    private String country;
    
    private String state;
    
    private String city;

    private String zipCode;

    private String addType;

    private String deleteSw;//address deleted or not

    private Integer customerId;

}
