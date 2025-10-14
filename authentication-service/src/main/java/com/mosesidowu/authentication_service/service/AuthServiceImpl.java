package com.mosesidowu.authentication_service.service;

import com.mosesidowu.authentication_service.data.model.User;
import com.mosesidowu.authentication_service.data.repository.UserRepository;
import com.mosesidowu.authentication_service.dto.request.AuthRequest;
import com.mosesidowu.authentication_service.dto.request.RegisterRequest;
import com.mosesidowu.authentication_service.dto.response.AuthResponse;
import com.mosesidowu.authentication_service.dto.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            log.info("User with phone number {} already exists", request.getPhoneNumber());
            throw new RuntimeException("User with this phone number already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.valueOf(request.getRole().toUpperCase()))
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .message("User registered successfully")
                .userId(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getId(), user.getPhoneNumber(), user.getRole().name());

        return LoginResponse.builder()
                .message("Login successful")
                .token(token)
                .role(user.getRole().name())
                .build();
    }

}
