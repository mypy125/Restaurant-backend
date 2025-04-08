package com.mygitgor.restaurant.infrastructure.database.jpa;

import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String username);
}
