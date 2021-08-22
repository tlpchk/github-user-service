package com.empik.recruitment.homework.service.rest;

import com.empik.recruitment.homework.model.LoginCounter;
import com.empik.recruitment.homework.repository.LoginCounterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class LoginCounterService {
    private final LoginCounterRepository loginCounterRepository;

    public void incrementCounter(String login) {
        var counter = loginCounterRepository.findById(login).orElse(new LoginCounter(login));
        counter.setRequestCount(counter.getRequestCount() + 1);
        log.info("Login counter for '{}' is incremented", login);
        loginCounterRepository.save(counter);
    }
}
