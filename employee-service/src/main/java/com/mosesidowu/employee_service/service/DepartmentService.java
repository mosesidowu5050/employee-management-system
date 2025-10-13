package com.mosesidowu.employee_service.service;


import com.mosesidowu.employee_service.dto.request.DepartmentRequest;
import com.mosesidowu.employee_service.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest request);
    DepartmentResponse updateDepartment(Long id, DepartmentRequest request);
    void deleteDepartment(Long id);
    DepartmentResponse getDepartmentById(Long id);
    List<DepartmentResponse> getAllDepartments();
}
