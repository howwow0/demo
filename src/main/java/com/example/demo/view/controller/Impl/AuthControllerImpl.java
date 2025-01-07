package com.example.demo.view.controller.Impl;

import com.example.demo.model.dto.request.RefreshTokenRequest;
import com.example.demo.model.dto.request.SignInRequest;
import com.example.demo.model.dto.request.SignUpRequest;
import com.example.demo.model.dto.response.exception.AlreadyExistsException;
import com.example.demo.model.dto.response.exception.InvalidJwtException;
import com.example.demo.model.dto.response.message.JwtAuthenticationResponse;
import com.example.demo.model.service.AuthenticationService;
import com.example.demo.view.controller.AuthController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {
    private final AuthenticationService authenticationService;

    public AuthControllerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest) throws AlreadyExistsException {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest signInRequest) throws InvalidJwtException {
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/refresh")
    public JwtAuthenticationResponse refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws InvalidJwtException {
        return authenticationService.refreshAccessToken(refreshTokenRequest);
    }
}
