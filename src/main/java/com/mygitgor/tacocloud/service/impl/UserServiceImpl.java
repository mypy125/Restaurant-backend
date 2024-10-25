package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.config.JwtProvider;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.dto.UserProfileDto;
import com.mygitgor.tacocloud.repository.UserRepository;
import com.mygitgor.tacocloud.service.UserService;
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
    public User findUserByJwtToken(String jwt) throws Exception {
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
    public User findUserByEmail(String email) throws Exception{
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

    @Override
    public void updateUserProfile(UserProfileDto userProfileDto, String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);

        if (userProfileDto.getFullName() != null) {
            user.setFullName(userProfileDto.getFullName());
        }
        if (userProfileDto.getEmail() != null) {
            user.setEmail(userProfileDto.getEmail());
        }
        userRepository.save(user);
    }


}
