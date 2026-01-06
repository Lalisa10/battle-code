package com.example.battlecode_be.dto; 
import lombok.Builder; 
import lombok.Data; 
 
import java.util.Set; 
 
@Data 
@Builder 
public class LoginResponse { 
    private String token; 
    private String tokenType; 
    private String handle; 
    private String email; 
    private Set<String> roles; 
} 
