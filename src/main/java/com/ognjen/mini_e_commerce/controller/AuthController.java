package com.ognjen.mini_e_commerce.controller;


import com.ognjen.mini_e_commerce.dto.AuthResponse;
import com.ognjen.mini_e_commerce.dto.LoginRequest;
import com.ognjen.mini_e_commerce.dto.RegisterRequest;
import com.ognjen.mini_e_commerce.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and get JWT token")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Received login request for email: {}", loginRequest.email());
        try {
            AuthResponse response = authService.authenticateUser(loginRequest);
            logger.info("Login successful for user: {}", loginRequest.email());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Login failed for user {}: {}", loginRequest.email(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        logger.info("Received register request for email: {}", registerRequest.email());
        try {
            AuthResponse response = authService.registerUser(registerRequest);
            logger.info("Registration successful for user: {}", registerRequest.email());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Email is already in use")) {
                logger.warn("Registration failed - email already exists: {}", registerRequest.email());
                return ResponseEntity.badRequest().build();
            }
            logger.error("Registration failed for user {}: {}", registerRequest.email(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
