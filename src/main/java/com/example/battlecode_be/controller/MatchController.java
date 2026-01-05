package com.example.battlecode_be.controller;

import com.example.battlecode_be.dto.Create1v1MatchRequest;
import com.example.battlecode_be.dto.Create1v1MatchResponse;
import com.example.battlecode_be.model.Match;
import com.example.battlecode_be.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @PostMapping("/1v1")
    public Create1v1MatchResponse create1v1Match(@RequestBody Create1v1MatchRequest create1V1MatchRequest) {
        Match match = matchService.create1v1Match(create1V1MatchRequest);
        return Create1v1MatchResponse.builder()
                .id(match.getId())
                .tournamentId(match.getTournament().getId())
                .problemCode(create1V1MatchRequest.getProblemCode())
                .submission1Id(create1V1MatchRequest.getSubmission1Id())
                .submission2Id(create1V1MatchRequest.getSubmission2Id())
                .status(match.getStatus())
                .build();
    }
}
