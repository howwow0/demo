package com.example.demo.model.service;

import com.example.demo.model.dto.request.RefreshTokenRequest;
import com.example.demo.model.dto.request.SignInRequest;
import com.example.demo.model.dto.request.SignUpRequest;
import com.example.demo.model.dto.response.exception.AlreadyExistsException;
import com.example.demo.model.dto.response.exception.InvalidJwtException;
import com.example.demo.model.dto.response.message.JwtAuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthenticationService {
    JwtAuthenticationResponse signUp(@Valid SignUpRequest request) throws AlreadyExistsException;

    JwtAuthenticationResponse signIn(@Valid SignInRequest request) throws InvalidJwtException;

    JwtAuthenticationResponse refreshAccessToken(@Valid RefreshTokenRequest request) throws InvalidJwtException;

}
