package com.example.battlecode_be.service;


import com.example.battlecode_be.dto.CreateUserRequest;
import com.example.battlecode_be.dto.UserResponse;
import com.example.battlecode_be.model.Role;
import com.example.battlecode_be.model.User;
import com.example.battlecode_be.repository.RoleRepository;
import com.example.battlecode_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createPlayer(CreateUserRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.existsByHandle(req.getHandle())) {
            throw new IllegalArgumentException("Handle already exists");
        }

        Role playerRole = roleRepository.findByName(Role.RoleName.PLAYER)
                .orElseThrow(() -> new IllegalStateException("PLAYER role not found"));

        User user = User.builder()
                .handle(req.getHandle())
                .email(req.getEmail())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .roles(Set.of(playerRole))
                .isActive(true)
                .build();

        return userRepository.save(user);
    }


    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void deactivateUser(Long id) {
        User user = getById(id);
        user.setActive(false);
        userRepository.save(user);
    }

    public void activateUser(Long id) {
        User user = getById(id);
        user.setActive(true);
        userRepository.save(user);
    }
}
