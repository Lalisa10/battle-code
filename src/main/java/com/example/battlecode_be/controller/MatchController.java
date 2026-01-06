package com.example.battlecode_be.controller;

import com.example.battlecode_be.dto.Create1v1MatchRequest;
import com.example.battlecode_be.dto.Create1v1MatchResponse;
import com.example.battlecode_be.dto.MatchResponse;
import com.example.battlecode_be.dto.UpdateMatchRequest;
import com.example.battlecode_be.model.Match;
import com.example.battlecode_be.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
                .status(String.valueOf(match.getStatus()))
                .build();
    }

    @PostMapping("/{matchId}/start")
    public ResponseEntity<?> startMatch(@PathVariable Long matchId) {

        matchService.startMatch(matchId);

        return ResponseEntity.accepted().body(Map.of(
                "matchId", matchId,
                "status", "RUNNING"
        ));
    }

    @GetMapping("/{id}")
    public MatchResponse getMatch(@PathVariable Long id) {
        return matchService.getMatchResponse(id);
    }

    @PutMapping("/{id}")
    public MatchResponse updateMatch(
            @PathVariable Long id,
            @RequestBody UpdateMatchRequest request
    ) {
        return matchService.updateMatch(id, request);
    }

}
