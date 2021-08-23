package com.simple.rest.app.controller;

import com.simple.rest.app.model.User;
import com.simple.rest.app.service.rest.LoginCounterService;
import com.simple.rest.app.service.rest.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @Mock
    private LoginCounterService loginCounterService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController cut;

    @Test
    public void getUserInfo_CallIncrementCounter() {
        // given
        var user = getTestUser();
        var login = user.getLogin();

        // when
        cut.getUserInfo(user.getLogin());

        // then
        verify(userService, times(1)).getUser(login);
        verify(loginCounterService, times(1)).incrementCounter(login);
    }

    private User getTestUser() {
        var user = new User();
        user.setId(123);
        user.setLogin("testLogin");
        return user;
    }
}
