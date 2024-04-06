package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
