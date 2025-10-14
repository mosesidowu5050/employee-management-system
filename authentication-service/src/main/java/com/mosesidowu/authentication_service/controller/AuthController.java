package com.mosesidowu.authentication_service.controller;

import com.mosesidowu.authentication_service.dto.request.AuthRequest;
import com.mosesidowu.authentication_service.dto.request.RegisterRequest;
import com.mosesidowu.authentication_service.dto.response.AuthResponse;
import com.mosesidowu.authentication_service.dto.response.LoginResponse;
import com.mosesidowu.authentication_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
