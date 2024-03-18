package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.taco.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
