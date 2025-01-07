package com.example.demo.view.controller;

import com.example.demo.model.dto.request.UserRequest;
import com.example.demo.model.dto.response.entity.UserResponse;
import com.example.demo.model.dto.response.exception.AlreadyExistsException;
import com.example.demo.model.dto.response.exception.NotFoundException;
import com.example.demo.model.dto.response.message.DeletedResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "Auth")
public interface UserController {
    Iterable<UserResponse> findAll();
    UserResponse findUserById(Integer id) throws NotFoundException;
    UserResponse update(Integer id, UserRequest request) throws NotFoundException, AlreadyExistsException;
    DeletedResponse delete(Integer id) throws NotFoundException;
    void getAdmin() throws NotFoundException;
}
