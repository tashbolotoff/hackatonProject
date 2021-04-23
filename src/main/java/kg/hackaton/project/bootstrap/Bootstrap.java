package kg.hackaton.project.bootstrap;


import kg.hackaton.project.entities.User;
import kg.hackaton.project.entities.UserRole;
import kg.hackaton.project.repositories.UserRepo;
import kg.hackaton.project.repositories.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${filespath}")
    private String filespath;

    @Override
    public void run(String... args) throws Exception {
        // ROLES
        UserRole adminRole = UserRole.builder()
                .name("ROLE_ADMIN")
                .build();
        userRoleRepo.save(adminRole);

        // USERS
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .userRole(adminRole)
                .build();
        userRepo.save(admin);
    }
}