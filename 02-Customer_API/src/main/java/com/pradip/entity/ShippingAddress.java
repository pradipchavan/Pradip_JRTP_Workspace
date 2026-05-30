package com.pradip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shipping_addr")
@Setter
@Getter
public class ShippingAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addrId;
	
	private String houseNo;

    private String street;

    //@ManyToOne
    //@JoinColumn(name = "country_id")
    private String country;
    
    //@ManyToOne
    //@JoinColumn(name = "state_id")
    private String state;
    
    //@ManyToOne
    //@JoinColumn(name = "city_id")
    private String city;

    private String zipCode;

    private String addType;

    private String deleteSw;//address deleted or not

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
