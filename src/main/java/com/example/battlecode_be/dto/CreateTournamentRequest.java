package com.example.battlecode_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTournamentRequest {
    private String name;
    private String organizerHandle;
    private String problemCode;
}
