package com.pradip.mapper;

import com.pradip.dto.ShippingAddressDTO;
import com.pradip.entity.ShippingAddress;
import org.modelmapper.ModelMapper;

public class ShippingAddressMapper {

    public  static final ModelMapper mapper = new ModelMapper();

    public static ShippingAddressDTO convertToDto(ShippingAddress address) {
        return mapper.map(address, ShippingAddressDTO.class);
    }

    public static ShippingAddress convertToEntity(ShippingAddressDTO addressDTO) {
        return mapper.map(addressDTO, ShippingAddress.class);
    }
}
