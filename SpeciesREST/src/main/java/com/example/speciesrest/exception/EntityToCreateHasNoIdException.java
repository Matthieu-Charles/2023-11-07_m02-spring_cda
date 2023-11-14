package com.example.speciesrest.exception;

public class EntityToCreateHasNoIdException extends RuntimeException{

    public EntityToCreateHasNoIdException(String s) {
        super(s);
    }

}
