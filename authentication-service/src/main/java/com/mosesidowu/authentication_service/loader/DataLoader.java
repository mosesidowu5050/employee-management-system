// authentication-service/src/main/java/com/darum/authenticationservice/DataLoader.java
package com.mosesidowu.authentication_service.loader;

import com.mosesidowu.authentication_service.data.model.Role;
import com.mosesidowu.authentication_service.data.model.User;
import com.mosesidowu.authentication_service.data.repository.RoleRepository;
import com.mosesidowu.authentication_service.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create Roles if they don't exist
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
        }
        if (roleRepository.findByName("ROLE_MANAGER").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_MANAGER"));
        }
        if (roleRepository.findByName("ROLE_EMPLOYEE").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_EMPLOYEE"));
        }

        // Create an Admin user if not exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("password"));
            Set<Role> adminRoles = new HashSet<>();
            roleRepository.findByName("ROLE_ADMIN").ifPresent(adminRoles::add);
            adminUser.setRoles(adminRoles);
            userRepository.save(adminUser);
        }

        // Create an Employee user if not exists
        if (userRepository.findByUsername("employee").isEmpty()) {
            User employeeUser = new User();
            employeeUser.setUsername("employee");
            employeeUser.setEmail("employee@example.com");
            employeeUser.setPassword(passwordEncoder.encode("password"));
            Set<Role> employeeRoles = new HashSet<>();
            roleRepository.findByName("ROLE_EMPLOYEE").ifPresent(employeeRoles::add);
            employeeUser.setRoles(employeeRoles);
            userRepository.save(employeeUser);
        }

        // Create a Manager user if not exists
        if (userRepository.findByUsername("manager").isEmpty()) {
            User managerUser = new User();
            managerUser.setUsername("manager");
            managerUser.setEmail("manager@example.com");
            managerUser.setPassword(passwordEncoder.encode("password"));
            Set<Role> managerRoles = new HashSet<>();
            roleRepository.findByName("ROLE_MANAGER").ifPresent(managerRoles::add);
            managerUser.setRoles(managerRoles);
            userRepository.save(managerUser);
        }
    }
}