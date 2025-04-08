package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.AddressEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.AddressDto;
import com.mygitgor.restaurant.api.exceprion.addressException.AddressAlreadyExistsException;
import com.mygitgor.restaurant.api.exceprion.addressException.AddressNotFoundException;
import com.mygitgor.restaurant.api.exceprion.addressException.UnauthorizedAddressAccessException;
import com.mygitgor.restaurant.model.repository.AddressRepository;
import com.mygitgor.restaurant.application.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;


    @Override
    public AddressEntity saveUserAddress(AddressDto addressDto, UserEntity user) throws AddressAlreadyExistsException {
        Optional<AddressEntity> existingAddress = addressRepository.findByStreetAddressAndUser(addressDto.getStreetAddress(), user);

        if (existingAddress.isPresent()) {
            return existingAddress.get();
        }

        AddressEntity address = modelMapper.map(addressDto, AddressEntity.class);
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<AddressDto> getAllAddresses(UserEntity user) throws AddressNotFoundException {
        List<AddressEntity> addresses = addressRepository.findByUser(user);
        if (addresses.isEmpty()) {
            throw new AddressNotFoundException("No addresses found for the user.");
        }
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(Long addressId, UserEntity user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        AddressEntity address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("AddressEntity not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAddressAccessException("You do not have access to this address.");
        }

        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto, UserEntity user) throws AddressAlreadyExistsException {
        Optional<AddressEntity> existingAddress = addressRepository.findByStreetAddressAndUser(addressDto.getStreetAddress(), user);
        if (existingAddress != null) {
            throw new AddressAlreadyExistsException("AddressEntity already exists for this user.");
        }

        AddressEntity address = modelMapper.map(addressDto, AddressEntity.class);
        address.setUser(user);
        AddressEntity savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressDto.class);
    }

    @Override
    public void updateAddress(Long addressId, AddressDto addressDto, UserEntity user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        AddressEntity existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("AddressEntity not found"));

        if (!existingAddress.getUser().equals(user)) {
            throw new UnauthorizedAddressAccessException("You cannot update an address that does not belong to you.");
        }

        existingAddress.setStreetAddress(addressDto.getStreetAddress());
        existingAddress.setCity(addressDto.getCity());
        existingAddress.setStateProvince(addressDto.getStateProvince());
        existingAddress.setPostalCode(addressDto.getPostalCode());
        existingAddress.setCountry(addressDto.getCountry());

        addressRepository.save(existingAddress);
    }

    @Override
    public void deleteAddress(Long addressId, UserEntity user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        AddressEntity address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("AddressEntity not found"));

        if (!address.getUser().equals(user)) {
            throw new UnauthorizedAddressAccessException("You cannot delete an address that does not belong to you.");
        }

        addressRepository.delete(address);
    }
}
