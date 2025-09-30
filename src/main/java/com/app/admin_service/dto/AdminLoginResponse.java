package com.app.admin_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminLoginResponse {
    private String token;
    private String tokenType; // "Bearer"
    private long expiresAt;   // epoch millis
}

