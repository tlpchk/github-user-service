package com.empik.recruitment.homework.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String login) {
        super(String.format("User with login '%s' not found", login));
    }
}
