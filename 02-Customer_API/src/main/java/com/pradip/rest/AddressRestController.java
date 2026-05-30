package com.pradip.rest;

import com.pradip.dto.ShippingAddressDTO;
import com.pradip.response.ApiResponse;
import com.pradip.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
public class AddressRestController {

    @Autowired
    //@Autowired - 1.use Relfection API to access private variable 2. Unit testing becomes difficult
    private AddressService addressService;

    /*NOTE:USING @AllArgsConstructor - 1. this is dependency injection though constructor
    2. Unit testing of the class becomes easy

    public AddressRestController(AddressService addressService) {
        this.addressService = addressService;
    }*/

    @PostMapping("/address")
    public ResponseEntity<ApiResponse<ShippingAddressDTO>> saveAddress(@RequestBody ShippingAddressDTO addressDTO) {

        ApiResponse<ShippingAddressDTO> apiResponse = new ApiResponse<>();

        ShippingAddressDTO shippingAddressDTO = addressService.saveAddress(addressDTO);

        if (shippingAddressDTO != null) {
            apiResponse.setStatus(201);
            apiResponse.setMessage("Address saved");
            apiResponse.setData(shippingAddressDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } else {
            apiResponse.setStatus(500);
            apiResponse.setMessage("Failed to save Address");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-address/{customerId}")
    public ResponseEntity<ApiResponse<List<ShippingAddressDTO>>> getCustomerAddresses(@PathVariable Integer customerId) {

        ApiResponse<List<ShippingAddressDTO>> apiResponse = new ApiResponse<>();

        List<ShippingAddressDTO> customerAddr = addressService.getCustomerAddr(customerId);

        if (!customerAddr.isEmpty()) {
            apiResponse.setStatus(200);
            apiResponse.setMessage("Address fetched");
            apiResponse.setData(customerAddr);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(500);
            apiResponse.setMessage("Failed to fetch Address");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/address")
    public ResponseEntity<ApiResponse<ShippingAddressDTO>> updateAddress(@RequestBody ShippingAddressDTO addressDTO) {

        ApiResponse<ShippingAddressDTO> apiResponse = new ApiResponse<>();

        ShippingAddressDTO shippingAddressDTO = addressService.saveAddress(addressDTO);

        if (shippingAddressDTO != null) {
            apiResponse.setStatus(200);
            apiResponse.setMessage("Address updated");
            apiResponse.setData(shippingAddressDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } else {
            apiResponse.setStatus(500);
            apiResponse.setMessage("Failed to update Address");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/address/{addrId}")
    public ResponseEntity<ApiResponse<ShippingAddressDTO>> getAddress(@PathVariable Integer addrId) {

        ApiResponse<ShippingAddressDTO> apiResponse = new ApiResponse<>();

        ShippingAddressDTO shippingAddressDTO = addressService.getAddressByAddrId(addrId);

        if (shippingAddressDTO != null) {
            apiResponse.setStatus(200);
            apiResponse.setMessage("Address fetched");
            apiResponse.setData(shippingAddressDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(500);
            apiResponse.setMessage("Failed to fetch Address");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/address/{addrId}")
    public ResponseEntity<ApiResponse<String>> deleteAddress(@PathVariable Integer addrId) {

        ApiResponse<String> apiResponse = new ApiResponse<>();

        boolean deleteAddress = addressService.deleteAddress(addrId);

        if (deleteAddress) {
            apiResponse.setStatus(200);
            apiResponse.setMessage("Address Deleted");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(500);
            apiResponse.setMessage("Failed to delete Address");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
