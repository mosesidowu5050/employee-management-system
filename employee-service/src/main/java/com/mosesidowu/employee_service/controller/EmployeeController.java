package com.mosesidowu.employee_service.controller;

import com.mosesidowu.employee_service.dto.request.EmployeeRequest;
import com.mosesidowu.employee_service.dto.response.EmployeeResponse;
import com.mosesidowu.employee_service.service.employeeService.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // ✅ Create Employee
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-employee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ✅ Update Employee
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update-employee/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(response);
    }

    // ✅ Delete Employee
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }

    // ✅ Get Employees by Department
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartment(@PathVariable Long departmentId) {
        List<EmployeeResponse> responses = employeeService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(responses);
    }


    // ✅ Get All Employees
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/get-all-employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> responses = employeeService.getAllEmployees();
        return ResponseEntity.ok(responses);
    }


    // ✅ Get Employee by ID
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/get-employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse response = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(response);
    }
}
