package com.example.demo.model.service;

import com.example.demo.model.database.entities.User;
import com.example.demo.model.dto.request.UserRequest;
import com.example.demo.model.dto.response.entity.UserResponse;
import com.example.demo.model.dto.response.exception.AlreadyExistsException;
import com.example.demo.model.dto.response.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
    Iterable<UserResponse> findAll();
    UserResponse findUserById(Integer id) throws NotFoundException;
    UserResponse create(User user) throws AlreadyExistsException;
    UserResponse update(Integer id, @Valid UserRequest request) throws NotFoundException, AlreadyExistsException;
    void delete(Integer id) throws NotFoundException;
    void getAdmin() throws NotFoundException;
}
