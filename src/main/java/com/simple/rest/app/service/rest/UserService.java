package com.simple.rest.app.service.rest;

import com.simple.rest.app.exception.UserNotFoundException;
import com.simple.rest.app.model.User;
import com.simple.rest.app.model.UserDTO;
import com.simple.rest.app.model.mapper.UserMapper;
import com.simple.rest.app.service.calculations.UserCalculationsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service

public class UserService {

    private final RestTemplate restTemplate;
    private final UserCalculationsService calculationService;

    public UserService(
            @Qualifier("userRestTemplate") RestTemplate restTemplate,
            UserCalculationsService calculationService
    ) {
        this.restTemplate = restTemplate;
        this.calculationService = calculationService;
    }

    public User getUser(String login) throws UserNotFoundException {
        try {
            var userDto = restTemplate.getForObject(String.format("/%s", login), UserDTO.class);
            var user = UserMapper.INSTANCE.userDTOtoUser(userDto);
            var calculations = calculationService.calculate(userDto);
            user.setCalculations(calculations);
            return user;
        } catch (HttpClientErrorException.NotFound exception) {
            throw new UserNotFoundException(login);
        }
    }
}
