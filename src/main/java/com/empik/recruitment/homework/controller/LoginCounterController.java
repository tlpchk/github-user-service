package com.empik.recruitment.homework.controller;

import com.empik.recruitment.homework.exception.LoginCounterNotFoundException;
import com.empik.recruitment.homework.model.LoginCounter;
import com.empik.recruitment.homework.repository.LoginCounterRepository;
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

    private final LoginCounterRepository loginCounterRepository;

    @GetMapping("/")
    public List<LoginCounter> getAllCounts() {
        return loginCounterRepository.findAll();
    }

    @GetMapping("/{login}")
    public LoginCounter getLoginCounter(@PathVariable String login) {
        return loginCounterRepository.findById(login)
                .orElseThrow(() -> new LoginCounterNotFoundException(login));
    }
}
