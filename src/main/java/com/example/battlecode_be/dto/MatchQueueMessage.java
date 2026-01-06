package com.example.battlecode_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchQueueMessage {

    private Long matchId;
    private ProblemInfo problem;
    private List<SubmissionInfo> submissions;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProblemInfo {
        private String engine;
        private Integer boardSize;
        private Integer winCondition;
        private Integer timeLimitMs;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubmissionInfo {
        private Long submissionId;
        private Short slot;
        private String codeUrl;
    }
}


