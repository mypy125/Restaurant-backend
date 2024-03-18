package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.taco.User;

public interface UserService {
    User getById(Long id);
    User getByUserName(String username);
    User update(User user);
    User create(User user);
    void delete(Long id);
}
