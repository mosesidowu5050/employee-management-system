package com.mosesidowu.employee_service.controller;

import com.mosesidowu.employee_service.dto.request.DepartmentRequest;
import com.mosesidowu.employee_service.dto.response.DepartmentResponse;
import com.mosesidowu.employee_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-department")
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest request) {
        DepartmentResponse response = departmentService.createDepartment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update-department/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id, @RequestBody DepartmentRequest request) {
        DepartmentResponse response = departmentService.updateDepartment(id, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete-department/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get-department/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        DepartmentResponse response = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/get-all-departments")
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        List<DepartmentResponse> responses = departmentService.getAllDepartments();
        return ResponseEntity.ok(responses);
    }
}
