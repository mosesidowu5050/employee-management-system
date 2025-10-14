package com.mosesidowu.employee_service.service.employeeService;


import com.mosesidowu.employee_service.data.model.Department;
import com.mosesidowu.employee_service.data.model.Employee;
import com.mosesidowu.employee_service.data.repository.DepartmentRepository;
import com.mosesidowu.employee_service.data.repository.EmployeeRepository;
import com.mosesidowu.employee_service.dto.request.EmployeeRequest;
import com.mosesidowu.employee_service.dto.response.EmployeeResponse;
import com.mosesidowu.employee_service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {

        // TODO: Validate and fetch the department
        Department department = departmentRepository.getDepartmentById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        //TODO: Step 2: Create and populate employee entity
        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .department(department)
                .build();

        // TODO: Save to DB
        Employee saved = employeeRepository.save(employee);

        // TODO: Return response DTO
        return EmployeeResponse.builder()
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .phoneNumber(saved.getPhoneNumber())
                .department(department)
                .build();
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getDepartmentId()));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(employee);
        return mapToResponse(updatedEmployee, department);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        Department department = employee.getDepartment();
        return mapToResponse(employee, department);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> mapToResponse(employee, employee.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(employee -> mapToResponse(employee, employee.getDepartment()))
                .collect(Collectors.toList());
    }

    private EmployeeResponse mapToResponse(Employee employee, Department department) {
        return EmployeeResponse.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .department(department)
                .build();
    }
}
