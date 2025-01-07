package com.example.demo.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
public interface JwtService {
    boolean isTokenValid(String token, UserDetails userDetails);

    String extractUsername(String token);

    String generateToken(UserDetails userDetails, Date expirationDate);
}
