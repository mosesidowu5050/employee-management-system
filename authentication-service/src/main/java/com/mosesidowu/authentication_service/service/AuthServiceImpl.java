package com.mosesidowu.authentication_service.service;

import com.mosesidowu.authentication_service.data.model.Role;
import com.mosesidowu.authentication_service.data.model.User;
import com.mosesidowu.authentication_service.data.repository.RoleRepository;
import com.mosesidowu.authentication_service.data.repository.UserRepository;
import com.mosesidowu.authentication_service.dto.request.LoginRequest;
import com.mosesidowu.authentication_service.dto.request.RegisterRequest;
import com.mosesidowu.authentication_service.dto.response.JwtResponse;
import com.mosesidowu.authentication_service.exception.RoleNotFoundException;
import com.mosesidowu.authentication_service.exception.UserNotFoundException;
import com.mosesidowu.authentication_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new ResponseEntity<>("Error: Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        User user = createUserFromRequest(registerRequest);
        Set<Role> roles = assignRolesToUser(registerRequest.getRoles());
        user.setRoles(roles);

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException(loginRequest.getUsername()));

        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer",
                user.getId(),
                userDetails.getUsername(),
                user.getEmail(),
                roles));
    }

    private User createUserFromRequest(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return user;
    }

    private Set<Role> assignRolesToUser(Set<String> strRoles) {
        Set<Role> roles = new java.util.HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role employeeRole = roleRepository.findByName("ROLE_EMPLOYEE")
                    .orElseThrow(() -> new RoleNotFoundException("ROLE_EMPLOYEE"));
            roles.add(employeeRole);
            return roles;
        }

        strRoles.forEach(role -> {
            switch (role.toLowerCase()) {
                case "admin":
                    Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                            .orElseThrow(() -> new RoleNotFoundException("ROLE_ADMIN"));
                    roles.add(adminRole);
                    break;
                case "manager":
                    Role managerRole = roleRepository.findByName("ROLE_MANAGER")
                            .orElseThrow(() -> new RoleNotFoundException("ROLE_MANAGER"));
                    roles.add(managerRole);
                    break;
                default:
                    Role employeeRole = roleRepository.findByName("ROLE_EMPLOYEE")
                            .orElseThrow(() -> new RoleNotFoundException("ROLE_EMPLOYEE"));
                    roles.add(employeeRole);
            }
        });

        return roles;
    }
}