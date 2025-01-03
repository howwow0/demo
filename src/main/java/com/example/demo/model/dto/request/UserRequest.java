package com.example.demo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record UserRequest (
        @Length(min = 1, max = 25)
        @NotNull
        String firstName,

        @Length(min = 1, max = 25)
        @NotNull
        String lastName,

        @Length(min = 1, max = 256)
        @Email
        @NotNull
        String email,

        @Length(min = 1, max = 20)
        @NotNull
        @Pattern(regexp = "^\\+?[1-9]\\d{0,3}[-\\s]?\\d{1,14}$")
        String phone
){}
