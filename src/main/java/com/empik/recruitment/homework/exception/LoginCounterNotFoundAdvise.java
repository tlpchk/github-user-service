package com.empik.recruitment.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LoginCounterNotFoundAdvise {

    @ResponseBody
    @ExceptionHandler(LoginCounterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String loginCounterNotFoundHandler(LoginCounterNotFoundException exception){
        return exception.getMessage();
    }
}
