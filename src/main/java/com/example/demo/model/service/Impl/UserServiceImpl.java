package com.example.demo.model.service.Impl;

import com.example.demo.model.database.entities.Role;
import com.example.demo.model.database.entities.User;
import com.example.demo.model.database.repository.UserRepository;
import com.example.demo.model.dto.request.UserRequest;
import com.example.demo.model.dto.response.entity.UserResponse;
import com.example.demo.model.dto.response.exception.AlreadyExistsException;
import com.example.demo.model.dto.response.exception.NotFoundException;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public Iterable<UserResponse> findAll() {
        return userMapper.asListResponse(userRepository.findAll());
    }

    @Override
    public UserResponse findUserById(Integer id) throws NotFoundException {
        User user = userRepository.findEntityById(id);
        if(user == null) {
            throw new NotFoundException();
        }
        return userMapper.asResponse(user);
    }

    @Transactional
    @Modifying
    @Override
    public UserResponse create(User user) throws AlreadyExistsException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("User with this email already exists.");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("User with this username already exists.");
        }
        user = userRepository.save(user);
        return userMapper.asResponse(user);
    }

    @Transactional
    @Modifying
    @Override
    public UserResponse update(Integer id, UserRequest request) throws NotFoundException, AlreadyExistsException {
        User user = userRepository.findEntityById(id);

        if(user == null) {
            throw new NotFoundException();
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new AlreadyExistsException("User with this email already exists.");
        }
        if (userRepository.existsByUsername(request.username())) {
            throw new AlreadyExistsException("User with this username already exists.");
        }
        user = userMapper.update(user, request);
        return userMapper.asResponse(user);
    }

    @Transactional
    @Modifying
    @Override
    public void delete(Integer id) throws NotFoundException {
        User user = userRepository.findEntityById(id);
        if(user == null) {
            throw new NotFoundException();
        }
        userRepository.delete(user);
    }

    @Override
    public void getAdmin() throws NotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new NotFoundException();
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
    }
}
