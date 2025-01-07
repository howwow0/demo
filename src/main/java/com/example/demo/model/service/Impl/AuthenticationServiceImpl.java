package com.example.demo.model.service.Impl;

import com.example.demo.model.database.entities.RefreshToken;
import com.example.demo.model.database.entities.User;
import com.example.demo.model.database.repository.RefreshTokenRepository;
import com.example.demo.model.dto.request.RefreshTokenRequest;
import com.example.demo.model.dto.request.SignInRequest;
import com.example.demo.model.dto.request.SignUpRequest;
import com.example.demo.model.dto.response.exception.AlreadyExistsException;
import com.example.demo.model.dto.response.exception.InvalidJwtException;
import com.example.demo.model.dto.response.message.JwtAuthenticationResponse;
import com.example.demo.model.service.AuthenticationService;
import com.example.demo.model.service.JwtService;
import com.example.demo.model.service.UserService;
import com.example.demo.security.config.JwtConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Service
@Validated
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtConfig jwtConfig;

    public AuthenticationServiceImpl(
            UserService userService,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            RefreshTokenRepository refreshTokenRepository,
            JwtConfig jwtConfig
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws AlreadyExistsException {
        User user = new User(
                request.firstName(),
                request.lastName(),
                request.username(),
                passwordEncoder.encode(request.password()),
                request.email()
        );

        userService.create(user);

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        refreshTokenRepository.save(new RefreshToken(refreshToken, user.getUsername()));

        return new JwtAuthenticationResponse(accessToken, refreshToken);
    }

    @Override
    public JwtAuthenticationResponse refreshAccessToken(RefreshTokenRequest refreshTokenRequest) throws InvalidJwtException {
        String token = refreshTokenRequest.refreshToken();
        String extractedUsername = jwtService.extractUsername(token);
        if (extractedUsername == null) {
            throw new InvalidJwtException();
        }

        UserDetails currentUserDetails = userDetailsService.loadUserByUsername(extractedUsername);
        RefreshToken refreshToken = refreshTokenRepository.findByValue(token);
        if (refreshToken == null || !jwtService.isTokenValid(token, currentUserDetails) ||
                !currentUserDetails.getUsername().equals(refreshToken.getOwnerUsername())) {
            throw new InvalidJwtException();
        }

        String newAccessToken = generateAccessToken(currentUserDetails);
        return new JwtAuthenticationResponse(newAccessToken, refreshToken.getValue());
    }

    private String generateAccessToken(UserDetails user) {
        return jwtService.generateToken(user, new Date(System.currentTimeMillis() + jwtConfig.getAccessTokenExpiration()));
    }

    private String generateRefreshToken(UserDetails user) {
        return jwtService.generateToken(user, new Date(System.currentTimeMillis() + jwtConfig.getRefreshTokenExpiration()));
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) throws InvalidJwtException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserDetails user = userDetailsService.loadUserByUsername(request.email());

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        RefreshToken oldToken = refreshTokenRepository.findByOwnerUsername(user.getUsername());
        if (oldToken == null) {
            throw new InvalidJwtException();
        }

        oldToken.setValue(refreshToken);
        refreshTokenRepository.save(oldToken);

        return new JwtAuthenticationResponse(accessToken, refreshToken);
    }
}