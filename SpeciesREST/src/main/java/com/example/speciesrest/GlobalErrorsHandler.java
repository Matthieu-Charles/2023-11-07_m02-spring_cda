package com.example.speciesrest;

import com.example.speciesrest.AnimalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorsHandler {

    @ExceptionHandler({ AnimalNotFoundException.class })
    public ResponseEntity<Object> handleAnimalNotFoundException(AnimalNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

}
