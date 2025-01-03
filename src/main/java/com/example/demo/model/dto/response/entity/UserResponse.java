package com.example.demo.model.dto.response.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse  extends EntityResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;

}
