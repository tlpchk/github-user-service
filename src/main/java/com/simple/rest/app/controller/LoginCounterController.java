package com.simple.rest.app.controller;

import com.simple.rest.app.model.LoginCounter;
import com.simple.rest.app.service.rest.LoginCounterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/counter")
public class LoginCounterController {

    private final LoginCounterService loginCounterService;

    @GetMapping("/")
    public List<LoginCounter> getAllCounts() {
        return loginCounterService.findAll();
    }

    @GetMapping("/{login}")
    public LoginCounter getLoginCounter(@PathVariable String login) {
        return loginCounterService.findById(login);
    }
}
