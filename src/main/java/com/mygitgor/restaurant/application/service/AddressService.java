package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.AddressEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.AddressDto;
import com.mygitgor.restaurant.api.exceprion.addressException.AddressAlreadyExistsException;
import com.mygitgor.restaurant.api.exceprion.addressException.AddressNotFoundException;
import com.mygitgor.restaurant.api.exceprion.addressException.UnauthorizedAddressAccessException;

import java.util.List;

public interface AddressService {
    AddressEntity saveUserAddress(AddressDto addressDto, UserEntity user) throws AddressAlreadyExistsException;
    List<AddressDto> getAllAddresses(UserEntity user)throws AddressNotFoundException;
    AddressDto getAddressById(Long addressId, UserEntity user)throws AddressNotFoundException;
    AddressDto createAddress(AddressDto addressDTO, UserEntity user)throws AddressAlreadyExistsException;
    void updateAddress(Long addressId, AddressDto addressDTO, UserEntity user)throws AddressNotFoundException, UnauthorizedAddressAccessException;
    void deleteAddress(Long addressId, UserEntity user)throws AddressNotFoundException, UnauthorizedAddressAccessException;
}
