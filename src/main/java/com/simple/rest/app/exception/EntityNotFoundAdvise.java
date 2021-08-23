package com.simple.rest.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityNotFoundAdvise {

    @ResponseBody
    @ExceptionHandler(LoginCounterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String loginCounterNotFoundHandler(LoginCounterNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(UserNotFoundException exception) {
        return exception.getMessage();
    }
}
