package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    @SneakyThrows
    User findUserByJwtToken(String jwt);
    @SneakyThrows
    User findUserByEmail(String email);

}
