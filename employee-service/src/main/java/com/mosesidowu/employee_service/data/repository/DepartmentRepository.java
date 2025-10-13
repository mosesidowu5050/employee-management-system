package com.mosesidowu.employee_service.data.repository;

import com.mosesidowu.employee_service.data.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
