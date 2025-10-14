package com.mosesidowu.authentication_service.loader;


import com.mosesidowu.authentication_service.data.model.User;
import com.mosesidowu.authentication_service.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByPhoneNumber("08011112222")) {
            userRepository.save(User.builder()
                    .fullName("System Admin")
                    .phoneNumber("08011112222")
                    .password(passwordEncoder.encode("admin123"))
                    .role(User.Role.ADMIN)
                    .build());
            System.out.println("✅ Created Admin: 08011112222 / admin123");
        }

        if (!userRepository.existsByPhoneNumber("08022223333")) {
            userRepository.save(User.builder()
                    .fullName("Department Manager")
                    .phoneNumber("08022223333")
                    .password(passwordEncoder.encode("manager123"))
                    .role(User.Role.MANAGER)
                    .build());
            System.out.println("✅ Created Manager: 08022223333 / manager123");
        }

        if (!userRepository.existsByPhoneNumber("08033334444")) {
            userRepository.save(User.builder()
                    .fullName("Regular Employee")
                    .phoneNumber("08033334444")
                    .password(passwordEncoder.encode("employee123"))
                    .role(User.Role.EMPLOYEE)
                    .build());
            System.out.println("✅ Created Employee: 08033334444 / employee123");
        }
    }
}
