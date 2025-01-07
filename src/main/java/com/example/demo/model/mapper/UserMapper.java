package com.example.demo.model.mapper;

import com.example.demo.model.database.entities.User;
import com.example.demo.model.dto.request.UserRequest;
import com.example.demo.model.dto.response.entity.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserResponse asResponse(User user) {
        return new UserResponse(user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getId(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getRole()
        );
    }

    public User update(User user, UserRequest request) {
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        return user;
    }

    public Iterable<UserResponse> asListResponse(Iterable<User> users) {
        List<UserResponse> response = new ArrayList<>();
        users.forEach(user -> response.add(asResponse(user)));
        return response;
    }
}
