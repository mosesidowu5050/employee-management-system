package com.mosesidowu.authentication_service.service;

import com.mosesidowu.authentication_service.dto.request.AuthRequest;
import com.mosesidowu.authentication_service.dto.request.RegisterRequest;
import com.mosesidowu.authentication_service.dto.response.AuthResponse;
import com.mosesidowu.authentication_service.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    LoginResponse login(AuthRequest request);
}