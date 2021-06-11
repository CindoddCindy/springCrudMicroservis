package com.trymicroservice.mymicroservicetry.rest.exception;

public class UserIsSecuredException extends RuntimeException{

    public UserIsSecuredException(String message) {
        super(message);
    }
}
