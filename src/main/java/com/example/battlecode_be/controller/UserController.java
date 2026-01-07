package com.example.battlecode_be.controller;


import com.example.battlecode_be.dto.CreateUserRequest;
import com.example.battlecode_be.dto.UserResponse;
import com.example.battlecode_be.model.User;
import com.example.battlecode_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        User user = userService.createPlayer(request);
        return toResponse(user);
    }

    @GetMapping("/")
    public List<UserResponse> getAllUser() {
        return userService.getAllUser().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return toResponse(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                //.id(user.getId())
                .handle(user.getHandle())
                .email(user.getEmail())
                .isActive(user.isActive())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(role -> role.getName().name())
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
