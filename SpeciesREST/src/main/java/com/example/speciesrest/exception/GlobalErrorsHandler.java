package com.example.speciesrest.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorsHandler {
    @ExceptionHandler({ EntityNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleEntityNotFoundException(Exception exception, WebRequest request) {
        exception.printStackTrace();
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }
    @ExceptionHandler({ EntityToCreateHasAnIdException.class, EntityToCreateHasNoIdException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto EntityToCreateHasAnIdExceptionOrNoIdException(Exception exception, WebRequest request) {
        exception.printStackTrace();
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public InvalidEntityErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        exception.printStackTrace();
        return new InvalidEntityErrorDto(
                exception.getGlobalErrors(),
                exception.getFieldErrors(),
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }
}
