package com.example.battlecode_be.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String handle;
    private String email;
    private String password;
}
