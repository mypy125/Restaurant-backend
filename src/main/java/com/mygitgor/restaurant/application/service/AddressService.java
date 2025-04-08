package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.AddressEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.AddressDto;
import com.mygitgor.restaurant.api.exceprion.addressException.AddressAlreadyExistsException;
import com.mygitgor.restaurant.api.exceprion.addressException.AddressNotFoundException;
import com.mygitgor.restaurant.api.exceprion.addressException.UnauthorizedAddressAccessException;
import com.mygitgor.restaurant.model.domain.Address;
import com.mygitgor.restaurant.model.domain.User;

import java.util.List;

public interface AddressService {
    Address saveUserAddress(AddressDto addressDto, User user) throws AddressAlreadyExistsException;
    List<AddressDto> getAllAddresses(User user)throws AddressNotFoundException;
    AddressDto getAddressById(Long addressId, User user)throws AddressNotFoundException;
    AddressDto createAddress(AddressDto addressDTO, User user)throws AddressAlreadyExistsException;
    void updateAddress(Long addressId, AddressDto addressDTO, User user)throws AddressNotFoundException, UnauthorizedAddressAccessException;
    void deleteAddress(Long addressId, User user)throws AddressNotFoundException, UnauthorizedAddressAccessException;
}
