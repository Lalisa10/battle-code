package com.example.battlecode_be.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Create1v1MatchResponse {
    private Long id;
    private Long tournamentId;
    private String problemCode;
    private Long submission1Id;
    private Long submission2Id;
    private String status;
}
