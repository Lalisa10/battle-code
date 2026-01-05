package com.example.battlecode_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Create1v1MatchRequest {

    private String problemCode;

    private Long submission1Id;
    private Long submission2Id;

    private Long tournamentId;
}
