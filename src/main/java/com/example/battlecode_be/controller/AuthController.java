package com.example.battlecode_be.controller;

import com.example.battlecode_be.dto.CreateUserRequest;
import com.example.battlecode_be.dto.LoginRequest;
import com.example.battlecode_be.dto.LoginResponse;
import com.example.battlecode_be.dto.UserResponse;
import com.example.battlecode_be.model.User;
import com.example.battlecode_be.security.JwtTokenProvider;
import com.example.battlecode_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String jwt = jwtTokenProvider.generateToken(user);

        LoginResponse response = LoginResponse.builder()
                .token(jwt)
                .tokenType("Bearer")
                .handle(user.getHandle())
                .email(user.getEmail())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(role -> role.getName().name())
                                .collect(Collectors.toSet())
                )
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // In a stateless JWT system, we typically don't need server-side logout
        // However, we can implement token blacklisting if needed
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest request) {
        User user = userService.createPlayer(request);
        UserResponse response = UserResponse.builder()
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
        return ResponseEntity.ok(response);
    }
} 
