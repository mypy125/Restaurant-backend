package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.User;

public interface UserRepository  {
    User findByEmail(String email);
    User save(User user);
}
