package com.app.admin_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt-test")
public class JwtTestController {

    @GetMapping
    public ResponseEntity<String> getSecureInfo() {
        return ResponseEntity.ok("✅ You accessed a secure admin endpoint!");
    }

}
