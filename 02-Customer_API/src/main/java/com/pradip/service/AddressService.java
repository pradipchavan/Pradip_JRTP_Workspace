package com.pradip.service;

import com.pradip.dto.ShippingAddressDTO;

import java.util.List;

public interface AddressService {

    public ShippingAddressDTO saveAddress(ShippingAddressDTO addressDTO);

    public ShippingAddressDTO getAddressByAddrId(Integer addrId);

    public List<ShippingAddressDTO> getCustomerAddr(Integer customerId);

    public boolean deleteAddress(Integer addrId);
}
