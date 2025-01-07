package com.example.demo.view.controller;

import com.example.demo.model.dto.request.RefreshTokenRequest;
import com.example.demo.model.dto.request.SignInRequest;
import com.example.demo.model.dto.request.SignUpRequest;
import com.example.demo.model.dto.response.exception.AlreadyExistsException;
import com.example.demo.model.dto.response.exception.InvalidJwtException;
import com.example.demo.model.dto.response.message.JwtAuthenticationResponse;

public interface AuthController {
    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) throws AlreadyExistsException;

    JwtAuthenticationResponse signIn(SignInRequest signInRequest) throws InvalidJwtException;

    JwtAuthenticationResponse refreshAccessToken(RefreshTokenRequest refreshTokenRequest) throws InvalidJwtException;

}
