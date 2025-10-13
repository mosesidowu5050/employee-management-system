package com.mosesidowu.authentication_service.dto.request;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RegisterRequest {
    private String fullName;
    private String phoneNumber;
    private String password;
    private String role; // ADMIN, MANAGER, EMPLOYEE
}
