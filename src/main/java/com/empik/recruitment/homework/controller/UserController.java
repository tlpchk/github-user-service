package com.empik.recruitment.homework.controller;

import com.empik.recruitment.homework.model.User;
import com.empik.recruitment.homework.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{login}")
    public User getUserInfo(@PathVariable(value="login") String login) {
        return userService.getUser(login);
    }

}
