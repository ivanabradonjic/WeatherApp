package com.valcon.WeatherApp.exception;

import com.valcon.WeatherApp.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidIntervalParametersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleResourceAlreadyExistsException(InvalidIntervalParametersException exception) {
        return new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.toString(), exception.getMessage());

    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleResourceAlreadyExistsException(ResourceNotFoundException exception) {
        return new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.toString(), exception.getMessage());

    }
    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleResourceAlreadyExistsException(BusinessLogicException exception) {
        return new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.toString(), exception.getMessage());

    }
}
