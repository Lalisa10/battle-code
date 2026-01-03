package com.example.battlecode_be.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProblemRequest {
    private String code;
    private String name;
    private String description;
    private String engineType;
    private Integer timeLimitMs;
    private Integer memoryLimitMb;
}
