package com.app.admin_service.service;

import com.app.admin_service.dto.Hobbies;
import com.app.admin_service.dto.User;
import com.app.admin_service.util.ApiResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class UserClientService {

    private final WebClient webClient;

    public UserClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<User> fetchAllUsers() {
        ApiResponse<List<User>> response = webClient.get()
                .uri("/user/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<User>>>() {})
                .block();

        return response.getData();
    }

    public List<Hobbies> fetchAllHobbies() {
        ApiResponse<List<Hobbies>> response = webClient.get()
                .uri("/hobbies/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<Hobbies>>>() {})
                .block();

        return response.getData();
    }
}

