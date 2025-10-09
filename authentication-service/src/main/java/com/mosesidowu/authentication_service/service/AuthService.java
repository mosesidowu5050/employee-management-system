package com.mosesidowu.authentication_service.service;

import com.mosesidowu.authentication_service.dto.request.LoginRequest;
import com.mosesidowu.authentication_service.dto.request.RegisterRequest;
import com.mosesidowu.authentication_service.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> registerUser(RegisterRequest registerRequest);
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
}