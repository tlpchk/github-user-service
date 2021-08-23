package com.simple.rest.app.service.rest;

import com.simple.rest.app.exception.LoginCounterNotFoundException;
import com.simple.rest.app.model.LoginCounter;
import com.simple.rest.app.repository.LoginCounterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class LoginCounterService {

    private final LoginCounterRepository loginCounterRepository;

    public List<LoginCounter> findAll() {
        return loginCounterRepository.findAll();
    }

    public LoginCounter findById(String login) throws LoginCounterNotFoundException {
        return loginCounterRepository.findById(login)
                .orElseThrow(() -> new LoginCounterNotFoundException(login));
    }

    public void incrementCounter(String login) {
        var counter = loginCounterRepository.findById(login).orElse(new LoginCounter(login));
        counter.setRequestCount(counter.getRequestCount() + 1);
        log.info("Login counter for '{}' is incremented", login);
        loginCounterRepository.save(counter);
    }
}
