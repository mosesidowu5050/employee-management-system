package com.mosesidowu.authentication_service.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder

public class AuthResponse {

    private String token;
    private String role;
    private Long userId;
}