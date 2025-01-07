package com.example.demo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record SignInRequest (
        @Email
        @NotNull
        String email,

        @Length(min = 5, max = 25)
        @NotNull
        String password
) {
}
