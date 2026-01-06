package com.example.battlecode_be.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResponse {
    private Long matchId;
    private Long problemCode;
    private Long tournamentId;
    private UserResponse winner;
    private String status;
    private String eventsUrl;
}
