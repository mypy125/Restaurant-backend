package com.mygitgor.restaurant.repository;

import com.mygitgor.restaurant.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
