package com.app.admin_service.controller;

import com.app.admin_service.dto.AdminRegistrationDto;
import com.app.admin_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationDto dto) {
        String result = adminService.registerAdmin(dto);
        return ResponseEntity.ok(result);
    }
}

