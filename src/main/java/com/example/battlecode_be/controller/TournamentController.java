package com.example.battlecode_be.controller;

import com.example.battlecode_be.dto.CreateTournamentRequest;
import com.example.battlecode_be.dto.CreateTournamentResponse;
import com.example.battlecode_be.model.Tournament;
import com.example.battlecode_be.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ORGANIZER') or hasRole('ADMIN')")
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
}
