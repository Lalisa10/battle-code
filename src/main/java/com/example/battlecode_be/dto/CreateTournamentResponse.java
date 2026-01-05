package com.example.battlecode_be.dto;

import com.example.battlecode_be.model.Tournament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTournamentResponse {
    private Long id;
    private String name;
    private String organizerHandle;
    private String problemCode;
    private String status;
}
