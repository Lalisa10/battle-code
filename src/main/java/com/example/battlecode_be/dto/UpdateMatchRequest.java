package com.example.battlecode_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMatchRequest {
    private String status;
    private Long winnerSubmissionId;
    private String eventsUrl;
}
