package com.example.battlecode_be.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    //private Long id;
    private String handle;
    private String email;
    private boolean isActive;
    private Set<String> roles;
}
