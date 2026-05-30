package com.pradip.mapper;

import com.pradip.dto.CustomerDTO;
import com.pradip.entity.Customer;
import org.modelmapper.ModelMapper;

public class CustomerMapper {

    public  static final ModelMapper mapper = new ModelMapper();

    public static  CustomerDTO convertToDto(Customer customerEntity) {
        return mapper.map(customerEntity, CustomerDTO.class);
    }

    public static Customer convertToEntity(CustomerDTO customerDTO) {
        return mapper.map(customerDTO, Customer.class);
    }
}
