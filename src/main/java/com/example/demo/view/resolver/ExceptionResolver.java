package com.example.demo.view.resolver;

import com.example.demo.model.dto.response.exception.AbstractApiException;
import com.example.demo.model.dto.response.exception.InvalidJwtException;
import com.example.demo.model.dto.response.exception.NotFoundException;
import com.example.demo.model.dto.response.exception.ValidationErrorException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.security.SignatureException;
import java.util.stream.Collectors;


@ControllerAdvice
@Component
public class ExceptionResolver {

    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<?> handle(AbstractApiException cause, WebRequest request) {
        return cause.asResponse();
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handle(SignatureException cause, WebRequest request) {
        return new InvalidJwtException().asResponse();
    }

    @ExceptionHandler(io.jsonwebtoken.SignatureException.class)
    public ResponseEntity<?> handle(io.jsonwebtoken.SignatureException cause, WebRequest request) {
        return new InvalidJwtException().asResponse();
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handle(MalformedJwtException cause, WebRequest request) {
        return new InvalidJwtException().asResponse();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handle(ExpiredJwtException cause, WebRequest request) {
        return new InvalidJwtException().asResponse();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handle(UsernameNotFoundException cause, WebRequest request) {
        return new NotFoundException().asResponse();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handle(ConstraintViolationException cause, WebRequest request) {
        return new ValidationErrorException(
                cause.getConstraintViolations().stream()
                        .map(v -> new ValidationErrorException.Violation(
                                v.getPropertyPath().toString(),
                                v.getMessage())
                        )
                        .collect(Collectors.toList())
        ).asResponse();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException cause, WebRequest request) {
        return new ValidationErrorException(
                cause.getBindingResult().getFieldErrors().stream()
                        .map(v -> new ValidationErrorException.Violation(
                                v.getField(),
                                v.getDefaultMessage())
                        )
                        .collect(Collectors.toList())
        ).asResponse();
    }
}