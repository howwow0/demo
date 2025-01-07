package com.example.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest (
        @NotBlank
        String refreshToken
){
}
