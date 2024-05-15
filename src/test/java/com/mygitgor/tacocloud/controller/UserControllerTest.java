package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    public void testFindUserByJwtToken() throws Exception {
        User user = new User();
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        ResponseEntity<User>response = userController.findUserByJwtToken("dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

}
