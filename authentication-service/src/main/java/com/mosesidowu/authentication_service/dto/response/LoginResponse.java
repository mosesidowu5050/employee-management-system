package com.mosesidowu.authentication_service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String message;
    private String role;
    private String token;

}
