package org.example.kinomichi.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.BindException;

@ControllerAdvice
public class GlobalExpeptionHandler {
    @ExceptionHandler(BindException.class)
    public String BindExeption(final BindException e) {
        {
            return "error";
        }
    }
}

