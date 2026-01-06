package com.example.battlecode_be.controller;

import com.example.battlecode_be.dto.CreateTournamentRequest;
import com.example.battlecode_be.dto.CreateTournamentResponse;
import com.example.battlecode_be.dto.MatchResponse;
import com.example.battlecode_be.dto.SubmissionResponse;
import com.example.battlecode_be.dto.TournamentResponse;
import com.example.battlecode_be.model.Tournament;
import com.example.battlecode_be.service.MatchService;
import com.example.battlecode_be.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;
    private final MatchService matchService;

    @GetMapping("/")
    public List<TournamentResponse> getAllTournaments() {
        return tournamentService.getAllTournaments()
                .stream()
                .map(this::toTournamentResponse)
                .toList();
    }

    @PostMapping("/")
    public CreateTournamentResponse createTournament(@RequestBody CreateTournamentRequest createTournamentRequest) {
        Tournament tournament = tournamentService.createTournament(createTournamentRequest);
        return CreateTournamentResponse.builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .organizerHandle(createTournamentRequest.getOrganizerHandle())
                .problemCode(createTournamentRequest.getProblemCode())
                .status(String.valueOf(tournament.getStatus()))
                .build();
    }

    @GetMapping("/{id}/matches")
    public List<MatchResponse> getMatchesByTournament(@PathVariable Long id) {
        return matchService.getMatchesByTournament(id);
    }

    @GetMapping("/{id}/submissions")
    public List<SubmissionResponse> getSubmissionsByTournament(@PathVariable Long id) {
        return matchService.getSubmissionsByTournament(id);
    }

    private TournamentResponse toTournamentResponse(Tournament tournament) {
        return TournamentResponse.builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .organizerHandle(tournament.getOrganizer().getHandle())
                .problemCode(tournament.getProblem().getCode())
                .status(String.valueOf(tournament.getStatus()))
                .build();
    }
}
