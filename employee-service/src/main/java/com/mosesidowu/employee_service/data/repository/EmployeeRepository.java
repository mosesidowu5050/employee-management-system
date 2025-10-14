package com.mosesidowu.employee_service.data.repository;

import com.mosesidowu.employee_service.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);
    Employee findByPhoneNumber(String phoneNumber);
    Optional<Employee> getByPhoneNumber(String phoneNumber);

    Optional<Employee> findEmployeeByPhoneNumber(String phoneNumber);
}
