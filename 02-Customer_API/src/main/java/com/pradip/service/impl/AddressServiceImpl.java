package com.pradip.service.impl;

import com.pradip.dto.ShippingAddressDTO;
import com.pradip.entity.Customer;
import com.pradip.entity.ShippingAddress;
import com.pradip.mapper.ShippingAddressMapper;
import com.pradip.repository.CustomerRepo;
import com.pradip.repository.ShippingAddressRepo;
import com.pradip.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ShippingAddressRepo addressRepo;

    @Override
    public ShippingAddressDTO saveAddress(ShippingAddressDTO addressDTO) {

        //orElseThrow() - if record not present it will throw NullPointerException
        Customer customer = customerRepo.findById(addressDTO.getCustomerId()).orElseThrow();

        ShippingAddress address = ShippingAddressMapper.convertToEntity(addressDTO);
        address.setCustomer(customer);
        address.setDeleteSw("No");

        ShippingAddress savedAddress = addressRepo.save(address);

        return ShippingAddressMapper.convertToDto(savedAddress);
    }

    @Override
    public ShippingAddressDTO getAddressByAddrId(Integer addrId) {
        ShippingAddress address = addressRepo.findById(addrId).orElseThrow();

        /*
         * ShippingAddressDTO addressDTO = new ShippingAddressDTO();
         *
         * BeanUtils.copyProperties(address, addressDTO);
         *
         * return addressDTO;
         *
         //return  mapper.map(address, ShippingAddressDTO.class);
         */

        return ShippingAddressMapper.convertToDto(address);
    }


    @Override
    public List<ShippingAddressDTO> getCustomerAddr(Integer customerId) {
        /*List<ShippingAddress> byCustomerId = addressRepo.findByCustomerCustomerId(customerId);
        return byCustomerId.stream()
                .map(address -> mapper.map(address, ShippingAddressDTO.class))
                .collect(Collectors.toList());*/

        return addressRepo.findByCustomerCustomerIdAndDeleteSw(customerId, "No")
                            .stream()
                            .map(ShippingAddressMapper::convertToDto)
                            .toList();
    }

    @Override
    public boolean deleteAddress(Integer addrId) {
        //Hard delete Address
        /*boolean exists = addressRepo.existsById(addrId);
        if (exists) {
            addressRepo.deleteById(addrId);
            return true;
        }*/

        //Soft delete Address
        Optional<ShippingAddress> addById = addressRepo.findById(addrId);
        if (addById.isPresent()) {
            ShippingAddress shippingAddress = addById.get();
            shippingAddress.setDeleteSw("Yes");
            addressRepo.save(shippingAddress);
            return true;
        }

        return false;
    }
}
