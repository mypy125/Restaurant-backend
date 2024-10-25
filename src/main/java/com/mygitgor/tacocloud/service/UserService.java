package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.dto.UserProfileDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserByJwtToken(String jwt)throws Exception;
    User findUserByEmail(String email)throws Exception;
    void updateUserProfile(UserProfileDto userProfileDto, String jwt) throws Exception;

}
