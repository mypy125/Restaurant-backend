package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.Address;
import com.mygitgor.restaurant.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface AddressRepository{
    Optional<Address> findByStreetAddressAndUser(String streetAddress, User user);
    List<Address> findByUser(User user);
    Optional<Address> findById(Long id);
    Address save(Address address);
    void delete(Address address);
}
