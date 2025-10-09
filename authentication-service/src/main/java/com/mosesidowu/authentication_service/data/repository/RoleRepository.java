package com.mosesidowu.authentication_service.data.repository;


import com.mosesidowu.authentication_service.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}