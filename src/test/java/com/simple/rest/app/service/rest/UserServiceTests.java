package com.simple.rest.app.service.rest;


import com.simple.rest.app.exception.UserNotFoundException;
import com.simple.rest.app.model.UserDTO;
import com.simple.rest.app.model.mapper.UserMapper;
import com.simple.rest.app.service.calculations.UserCalculationsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
    @Spy
    private UserMapper userMapper;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UserCalculationsService calculationService;

    @InjectMocks
    private UserService cut;

    private UserDTO testUserDTO;
    private double testCalculations;

    @BeforeEach
    void init() {
        userMapper = Mappers.getMapper(UserMapper.class);
        testUserDTO = getTestUserDTO();
        testCalculations = 100f;

        mockGetForObject(testUserDTO);
        when(calculationService.calculate(testUserDTO)).thenReturn(testCalculations);
    }

    private UserDTO getTestUserDTO() {
        var user = new UserDTO();
        user.setId(123);
        user.setLogin("testLogin");
        user.setFollowersCount(0);
        return user;
    }

    private void mockGetForObject(UserDTO userDTO) {
        var existingUserPath = String.format("/%s", userDTO.getLogin());
        when(restTemplate.getForObject(existingUserPath, UserDTO.class))
                .thenReturn(userDTO);
        when(restTemplate.getForObject(not(eq(existingUserPath)), eq(UserDTO.class)))
                .thenThrow(HttpClientErrorException.NotFound.class);
    }

    @Test
    public void getUser_CallsCalculationsServiceAndReturnsUserWhenItExists() {
        // given
        var expectedUser = userMapper.userDTOtoUser(testUserDTO);
        expectedUser.setCalculations(testCalculations);

        // when
        var actualUser = cut.getUser(testUserDTO.getLogin());

        // then
        verify(calculationService, times(1)).calculate(testUserDTO);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getUser_RaiseNotFoundExceptionWhenUserNotExists() {
        // given
        var login = "NON_EXISTING_USER";

        // when - then
        assertThrows(UserNotFoundException.class, () -> cut.getUser(login));
    }
}
