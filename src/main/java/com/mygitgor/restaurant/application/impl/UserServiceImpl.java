package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.infrastructure.sequrity.JwtProvider;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.UserProfileDto;
import com.mygitgor.restaurant.model.repository.UserRepository;
import com.mygitgor.restaurant.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * UserService: Этот сервиз связаны с аутентификацией и управлением пользователями.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    /**
     *  метод используется для поиска пользователя по JWT токену. Он принимает JWT токен в качестве аргумента,
     *  извлекает из него адрес электронной почты пользователя с помощью jwtProvider.getEmailFromJwtToken(jwt),
     *  а затем использует этот адрес электронной почты для поиска пользователя с помощью метода findUserByEmail(email).
     *  Если пользователь с таким адресом электронной почты найден, метод возвращает его объект. В противном случае генерируется исключение.
     * @param jwt переданный токен
     * @return возврошает пользователя
     * @throws Exception бросает исключения Exception
     */
    @Override
    public UserEntity findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return findUserByEmail(email);
    }

    /**
     * метод findUserByEmail используется для поиска пользователя по адресу электронной почты.
     * Он принимает адрес электронной почты в качестве аргумента и использует его для поиска пользователя в
     * репозитории пользователей. Если пользователь с таким адресом электронной почты найден,
     * метод возвращает его объект.В противном случае генерируется исключение UsernameNotFoundException.
     * @param email емаил аддрес
     * @return возврошает пользователя
     * @throws Exception бросает исключения UsernameNotFoundException
     */
    @Override
    public UserEntity findUserByEmail(String email) throws Exception{
        UserEntity user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

    @Override
    public void updateUserProfile(UserProfileDto userProfileDto, String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        UserEntity user = findUserByEmail(email);

        if (userProfileDto.getFullName() != null) {
            user.setFullName(userProfileDto.getFullName());
        }
        if (userProfileDto.getEmail() != null) {
            user.setEmail(userProfileDto.getEmail());
        }
        userRepository.save(user);
    }


}
