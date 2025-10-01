package com.app.admin_service.controller;

import com.app.admin_service.dto.Hobbies;
import com.app.admin_service.dto.User;
import com.app.admin_service.service.UserClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AdminUserController {

    private final UserClientService userClientService;

    public AdminUserController(UserClientService userClientService) {
        this.userClientService = userClientService;
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsersFromUserService() {
        List<User> users = userClientService.fetchAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/hobbies")
    public ResponseEntity<List<Hobbies>> getAllHobbies() {
        List<Hobbies> users = userClientService.fetchAllHobbies();
        return ResponseEntity.ok(users);
    }
}
