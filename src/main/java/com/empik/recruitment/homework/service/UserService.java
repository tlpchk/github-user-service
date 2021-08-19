package com.empik.recruitment.homework.service;

import com.empik.recruitment.homework.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    public UserService(@Qualifier("userRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUser(String login){
      return restTemplate.getForObject(String.format("/%s", login), User.class);
    }
}
