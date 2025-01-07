package com.example.demo.model.dto.response.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends AbstractApiException {

    public AlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
