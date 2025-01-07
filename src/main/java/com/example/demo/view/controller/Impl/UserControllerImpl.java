package com.example.demo.view.controller.Impl;

import com.example.demo.model.dto.request.UserRequest;
import com.example.demo.model.dto.response.entity.UserResponse;
import com.example.demo.model.dto.response.exception.AlreadyExistsException;
import com.example.demo.model.dto.response.exception.NotFoundException;
import com.example.demo.model.dto.response.message.DeletedResponse;
import com.example.demo.model.service.UserService;
import com.example.demo.view.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @Override
    public Iterable<UserResponse> findAll() {
        return userService.findAll();
    }

    @Override
    @GetMapping("/admin")
    public void getAdmin() throws NotFoundException {
        userService.getAdmin();
    }

    @GetMapping("/{id}")
    @Override
    public UserResponse findUserById(@PathVariable Integer id) throws NotFoundException {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    @Override
    public UserResponse update(@PathVariable Integer id,@RequestBody UserRequest request) throws NotFoundException, AlreadyExistsException {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Override
    public DeletedResponse delete(@PathVariable Integer id) throws NotFoundException {
        userService.delete(id);
        return new DeletedResponse();
    }
}
