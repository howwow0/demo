package com.example.demo.model.dto.response.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ValidationErrorException extends AbstractApiException {
    public record Violation(String fieldName, String message) {
    }

    private final List<Violation> violations;

    public ValidationErrorException(List<Violation> violations) {
        super(HttpStatus.BAD_REQUEST, "Validation error");
        this.violations = violations;
    }
}
