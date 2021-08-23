package com.simple.rest.app.exception;

public class LoginCounterNotFoundException extends RuntimeException {
    public LoginCounterNotFoundException(String login) {
        super(String.format("Counter for login '%s' not found", login));
    }
}
