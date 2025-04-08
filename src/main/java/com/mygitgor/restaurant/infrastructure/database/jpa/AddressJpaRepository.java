package com.mygitgor.restaurant.infrastructure.database.jpa;

import com.mygitgor.restaurant.infrastructure.database.entity.AddressEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
    Optional<AddressEntity> findByStreetAddressAndUser(String streetAddress, UserEntity user);
    List<AddressEntity> findByUser(UserEntity user);
}
