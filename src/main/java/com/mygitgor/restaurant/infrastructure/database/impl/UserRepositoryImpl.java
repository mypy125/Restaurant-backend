package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.UserJpaRepository;
import com.mygitgor.restaurant.mapper.UserMapper;
import com.mygitgor.restaurant.model.domain.User;
import com.mygitgor.restaurant.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;
    private final UserMapper userMapper;

    @Override
    public User findByEmail(String email){
        UserEntity userEntity = jpaRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userMapper.toDomain(userEntity);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedUserEntity = jpaRepository.save(userEntity);
        return userMapper.toDomain(savedUserEntity);
    }
}
