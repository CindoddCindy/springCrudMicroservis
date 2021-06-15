package com.trymicroservice.mymicroservicetry.rest.validation;

public interface BaseValidator<T> {

    void validate(T obj);
}
