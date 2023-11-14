package com.example.speciesrest.exception;

import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvalidEntityErrorDto {

    private final List<ObjectError> globalsErrors;
    private final List<FieldError> fieldsErrors;
    private final LocalDateTime localDateTime;
    private final String message;
    private final String description;

    public InvalidEntityErrorDto(List<ObjectError> globalsErrors, List<FieldError> fieldsErrors, LocalDateTime localDateTime, String message, String description) {
        this.globalsErrors = globalsErrors;
        this.fieldsErrors = fieldsErrors;
        this.localDateTime = localDateTime;
        this.message = message;
        this.description = description;
    }
}
