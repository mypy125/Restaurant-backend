package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.api.controller.DTOs.AddressDto;
import com.mygitgor.restaurant.api.exceprion.addressException.AddressAlreadyExistsException;
import com.mygitgor.restaurant.api.exceprion.addressException.AddressNotFoundException;
import com.mygitgor.restaurant.api.exceprion.addressException.UnauthorizedAddressAccessException;
import com.mygitgor.restaurant.mapper.AddressDtoMapper;
import com.mygitgor.restaurant.model.domain.Address;
import com.mygitgor.restaurant.model.domain.User;
import com.mygitgor.restaurant.model.repository.AddressRepository;
import com.mygitgor.restaurant.application.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoMapper dtoMapper;


    @Override
    public Address saveUserAddress(AddressDto addressDto, User user) throws AddressAlreadyExistsException {
        Optional<Address> existing = addressRepository.findByStreetAddressAndUser(addressDto.getStreetAddress(), user);
        if (existing.isPresent()) {
            return existing.get();
        }

        Address address = dtoMapper.toDomain(addressDto);
        address.setUserId(user.getId());
        return addressRepository.save(address);
    }

    @Override
    public List<AddressDto> getAllAddresses(User user) throws AddressNotFoundException {
        List<Address> addresses = addressRepository.findByUser(user);
        if (addresses.isEmpty()) throw new AddressNotFoundException("No addresses found");
        return dtoMapper.toDto(addresses);
    }

    @Override
    public AddressDto getAddressById(Long addressId, User user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        if (!address.getUserId().equals(user.getId()))
            throw new UnauthorizedAddressAccessException("Unauthorized");

        return dtoMapper.toDto(address);
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto, User user) throws AddressAlreadyExistsException {
        Optional<Address> existing = addressRepository.findByStreetAddressAndUser(addressDto.getStreetAddress(), user);
        if (existing.isPresent()) {
            throw new AddressAlreadyExistsException("Address already exists");
        }

        Address address = dtoMapper.toDomain(addressDto);
        address.setUserId(user.getId());
        return dtoMapper.toDto(addressRepository.save(address));
    }

    @Override
    public void updateAddress(Long addressId, AddressDto dto, User user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        Address existing = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Not found"));

        if (!existing.getUserId().equals(user.getId()))
            throw new UnauthorizedAddressAccessException("Forbidden");

        existing.setStreetAddress(dto.getStreetAddress());
        existing.setCity(dto.getCity());
        existing.setStateProvince(dto.getStateProvince());
        existing.setPostalCode(dto.getPostalCode());
        existing.setCountry(dto.getCountry());

        addressRepository.save(existing);
    }

    @Override
    public void deleteAddress(Long addressId, User user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        if (!address.getUserId().equals(user.getId()))
            throw new UnauthorizedAddressAccessException("Unauthorized");

        addressRepository.delete(address);
    }
}
