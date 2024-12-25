package com.mygitgor.restaurant.service;

import com.mygitgor.restaurant.domain.Address;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.dto.AddressDto;
import com.mygitgor.restaurant.exceptions.addressException.AddressAlreadyExistsException;
import com.mygitgor.restaurant.exceptions.addressException.AddressNotFoundException;
import com.mygitgor.restaurant.exceptions.addressException.UnauthorizedAddressAccessException;

import java.util.List;

public interface AddressService {
    Address saveUserAddress(AddressDto addressDto, User user) throws AddressAlreadyExistsException;
    List<AddressDto> getAllAddresses(User user)throws AddressNotFoundException;
    AddressDto getAddressById(Long addressId, User user)throws AddressNotFoundException;
    AddressDto createAddress(AddressDto addressDTO, User user)throws AddressAlreadyExistsException;
    void updateAddress(Long addressId, AddressDto addressDTO, User user)throws AddressNotFoundException, UnauthorizedAddressAccessException;
    void deleteAddress(Long addressId, User user)throws AddressNotFoundException, UnauthorizedAddressAccessException;
}
