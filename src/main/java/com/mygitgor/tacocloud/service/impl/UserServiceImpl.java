package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.config.JwtProvider;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.repository.UserRepository;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception{
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}
