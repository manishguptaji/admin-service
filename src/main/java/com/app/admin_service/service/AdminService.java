package com.app.admin_service.service;

import com.app.admin_service.dto.AdminRegistrationDto;
import com.app.admin_service.entity.Admins;
import com.app.admin_service.repo.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public String registerAdmin(AdminRegistrationDto dto) {
        // Check if username/email already exists
        if (adminRepository.findByUsername(dto.getUsername()).isPresent()) {
            return "Username already exists!";
        }
        if (adminRepository.findByEmail(dto.getEmail()).isPresent()) {
            return "Email already exists!";
        }

        // Encrypt password
        String hashedPwd = passwordEncoder.encode(dto.getPassword());

        // Save admin
        Admins admin = Admins.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(hashedPwd)
                .role("ADMIN")
                .build();

        adminRepository.save(admin);
        return "Admin registered successfully!";
    }

    public Optional<Admins> authenticate(String username, String rawPassword) {
        Optional<Admins> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admins admin = adminOpt.get();
            if (passwordEncoder.matches(rawPassword, admin.getPassword())) {
                return Optional.of(admin);
            }
        }
        return Optional.empty();
    }
}
