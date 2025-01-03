package com.example.demo.model.dto.response.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
public class AbstractApiMessage {
    protected final String message;
    protected final HttpStatus httpStatus = HttpStatus.OK;

    public ResponseEntity<AbstractApiMessage> asResponse(){
        return ResponseEntity.status(httpStatus).body(this);
    }
}
