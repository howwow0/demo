package com.example.demo.security.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("jwt")
@AllArgsConstructor
@Getter
public final class JwtConfig {
    @NotEmpty
    @NotNull
    private final String key;
    @Min(0)
    @NotNull
    private final long accessTokenExpiration;
    @Min(0)
    @NotNull
    private final long refreshTokenExpiration;
}
