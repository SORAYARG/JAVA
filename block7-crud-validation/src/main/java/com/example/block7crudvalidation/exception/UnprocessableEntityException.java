package com.example.block7crudvalidation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends HttpStatusCodeException {
    String message;
    public UnprocessableEntityException(String message){
        super(HttpStatus.UNPROCESSABLE_ENTITY);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}