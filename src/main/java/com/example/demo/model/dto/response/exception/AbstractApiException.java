package com.example.demo.model.dto.response.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage"})
public abstract class AbstractApiException extends Exception {

    protected final HttpStatus httpStatus;

    protected final String message;

   public AbstractApiException(){
       httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
       message = "An error occurred";
   }
    public ResponseEntity<AbstractApiException> asResponse(){
        return ResponseEntity.status(httpStatus).body(this);
    }
}
