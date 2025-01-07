package com.example.demo.model.dto.response.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public abstract class AbstractApiMessage {
    protected final String message;
    protected final HttpStatus httpStatus = HttpStatus.OK;
}
