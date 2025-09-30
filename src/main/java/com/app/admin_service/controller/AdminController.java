package com.app.admin_service.controller;

import com.app.admin_service.dto.AdminLoginRequest;
import com.app.admin_service.dto.AdminLoginResponse;
import com.app.admin_service.dto.AdminRegistrationDto;
import com.app.admin_service.service.AdminService;
import com.app.admin_service.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    public AdminController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationDto dto) {
        String result = adminService.registerAdmin(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> login(@RequestBody AdminLoginRequest request) {
        return adminService.authenticate(request.getUsername(), request.getPassword())
                .map(admin -> {
                    String token = jwtUtil.generateToken(admin.getUsername(), admin.getRole());
                    long expiresAt = jwtUtil.getExpirationDate(token).getTime();
                    return ResponseEntity.ok(new AdminLoginResponse(token, "Bearer", expiresAt));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AdminLoginResponse(null,null, 0)));
    }
}

