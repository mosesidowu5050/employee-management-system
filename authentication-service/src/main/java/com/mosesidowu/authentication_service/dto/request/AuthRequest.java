package com.mosesidowu.authentication_service.dto.request;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class AuthRequest {
    private String phoneNumber;
    private String password;
}