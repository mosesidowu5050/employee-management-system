package com.mosesidowu.authentication_service.data.repository;

import com.mosesidowu.authentication_service.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByPhoneNumber(String phoneNumber);
}