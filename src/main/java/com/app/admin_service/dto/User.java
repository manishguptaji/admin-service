package com.app.admin_service.dto;


import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String status;
    private String role;
    private String createdAt;
}