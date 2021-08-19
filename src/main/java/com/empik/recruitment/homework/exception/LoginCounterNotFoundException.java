package com.empik.recruitment.homework.exception;

public class LoginCounterNotFoundException extends RuntimeException{
    public LoginCounterNotFoundException(String login){
        super(String.format("Counter for login '%s' is not found", login));
    }
}
