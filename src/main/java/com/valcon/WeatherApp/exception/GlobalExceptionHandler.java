package com.valcon.WeatherApp.exception;

import com.valcon.WeatherApp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidIntervalParametersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleResourceAlreadyExistsException(InvalidIntervalParametersException exception) {
        return new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.toString(), exception.getMessage());

    }
}
