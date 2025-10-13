package com.mosesidowu.employee_service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long departmentId;
}
