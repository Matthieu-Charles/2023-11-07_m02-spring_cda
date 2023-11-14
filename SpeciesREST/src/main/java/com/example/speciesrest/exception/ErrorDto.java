package com.example.speciesrest.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDto {

    private final int statusCode;
    private final LocalDateTime localDateTime;
    private final String message;
    private final String description;

    public ErrorDto(int statusCode, LocalDateTime localDateTime, String message, String description) {
        this.statusCode = statusCode;
        this.localDateTime = localDateTime;
        this.message = message;
        this.description = description;
    }
}
