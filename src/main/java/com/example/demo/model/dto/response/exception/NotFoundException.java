package com.example.demo.model.dto.response.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractApiException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Entity not found");
    }
}
