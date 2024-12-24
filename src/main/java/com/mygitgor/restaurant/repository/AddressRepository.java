package com.mygitgor.restaurant.repository;

import com.mygitgor.restaurant.domain.Address;
import com.mygitgor.restaurant.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByStreetAddressAndUser(String streetAddress, User user);
    List<Address> findByUser(User user);
}
