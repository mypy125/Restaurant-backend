package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.AddressEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.AddressJpaRepository;
import com.mygitgor.restaurant.mapper.AddressMapper;
import com.mygitgor.restaurant.mapper.UserMapper;
import com.mygitgor.restaurant.model.domain.Address;
import com.mygitgor.restaurant.model.domain.User;
import com.mygitgor.restaurant.model.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {
    private final AddressJpaRepository jpaRepository;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    @Override
    public Optional<Address> findByStreetAddressAndUser(String streetAddress, User user) {
        return jpaRepository.findByStreetAddressAndUser(streetAddress, userMapper.toEntity(user))
                .map(addressMapper::toDomain);
    }

    @Override
    public List<Address> findByUser(User user) {
        return addressMapper.toDomain(jpaRepository.findByUser(userMapper.toEntity(user)));
    }

    @Override
    public Optional<Address> findById(Long id) {
        return jpaRepository.findById(id).map(addressMapper::toDomain);
    }

    @Override
    public Address save(Address address) {
        AddressEntity saved = jpaRepository.save(addressMapper.toEntity(address));
        return addressMapper.toDomain(saved);
    }

    @Override
    public void delete(Address address) {
        jpaRepository.delete(addressMapper.toEntity(address));
    }
}
