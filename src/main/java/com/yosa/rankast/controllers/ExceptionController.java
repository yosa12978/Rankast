package com.yosa.rankast.controllers;

import com.yosa.rankast.exceptions.BadRequestException;
import com.yosa.rankast.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFoundHandler(NotFoundException exception){
        return new HashMap<String, Object>()
                {{ put("status", 404); put("message", exception.getMessage()); }};
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> badRequestHandler(BadRequestException exception){
        return new HashMap<String, Object>()
                {{ put("status", 400); put("message", exception.getMessage()); }};
    }

}
