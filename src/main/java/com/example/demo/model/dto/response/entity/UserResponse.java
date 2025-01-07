package com.example.demo.model.dto.response.entity;

import com.example.demo.model.database.entities.Role;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse extends EntityResponse {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String username;
    private final Role role;

    public UserResponse(String firstName, String lastName, String email, String username, Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, Role role) {
        super(id, createdAt, updatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.username = username;

    }
}
