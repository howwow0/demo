package com.example.demo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record SignUpRequest (
        @Length(min = 1, max = 25)
        @NotNull
        String firstName,

        @Length(min = 1, max = 25)
        @NotNull
        String lastName,

        @Email
        @NotNull
        String email,

        @Length(min = 5, max = 35)
        @NotNull
        String username,

        @Length(min = 5, max = 25)
        @NotNull
        String password
) {
}
