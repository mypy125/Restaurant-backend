package com.mygitgor.restaurant.service.impl;

import com.mygitgor.restaurant.domain.Address;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.dto.AddressDto;
import com.mygitgor.restaurant.exceptions.addressException.AddressAlreadyExistsException;
import com.mygitgor.restaurant.exceptions.addressException.AddressNotFoundException;
import com.mygitgor.restaurant.exceptions.addressException.UnauthorizedAddressAccessException;
import com.mygitgor.restaurant.repository.AddressRepository;
import com.mygitgor.restaurant.service.AddressService;
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
    public Address saveUserAddress(AddressDto addressDto, User user) throws AddressAlreadyExistsException {
        Optional<Address> existingAddress = addressRepository.findByStreetAddressAndUser(addressDto.getStreetAddress(), user);

        if (existingAddress.isPresent()) {
            return existingAddress.get();
        }

        Address address = modelMapper.map(addressDto, Address.class);
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<AddressDto> getAllAddresses(User user) throws AddressNotFoundException {
        List<Address> addresses = addressRepository.findByUser(user);
        if (addresses.isEmpty()) {
            throw new AddressNotFoundException("No addresses found for the user.");
        }
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(Long addressId, User user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAddressAccessException("You do not have access to this address.");
        }

        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto, User user) throws AddressAlreadyExistsException {
        Optional<Address> existingAddress = addressRepository.findByStreetAddressAndUser(addressDto.getStreetAddress(), user);
        if (existingAddress != null) {
            throw new AddressAlreadyExistsException("Address already exists for this user.");
        }

        Address address = modelMapper.map(addressDto, Address.class);
        address.setUser(user);
        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressDto.class);
    }

    @Override
    public void updateAddress(Long addressId, AddressDto addressDto, User user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

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
    public void deleteAddress(Long addressId, User user) throws AddressNotFoundException, UnauthorizedAddressAccessException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        if (!address.getUser().equals(user)) {
            throw new UnauthorizedAddressAccessException("You cannot delete an address that does not belong to you.");
        }

        addressRepository.delete(address);
    }
}
