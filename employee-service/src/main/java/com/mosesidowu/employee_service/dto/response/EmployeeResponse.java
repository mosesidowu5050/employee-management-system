package com.mosesidowu.employee_service.dto.response;

import com.mosesidowu.employee_service.data.model.Department;
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
    private Department department;

}
