package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.repository.UserRepository;
import com.mygitgor.tacocloud.service.UserService;
import com.mygitgor.tacocloud.domain.taco.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User getByUserName(String username) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
