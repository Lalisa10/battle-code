package com.example.battlecode_be.service;

import com.example.battlecode_be.dto.CreateTournamentRequest;
import com.example.battlecode_be.model.Problem;
import com.example.battlecode_be.model.Tournament;
import com.example.battlecode_be.model.User;
import com.example.battlecode_be.repository.ProblemRepository;
import com.example.battlecode_be.repository.TournamentRepository;
import com.example.battlecode_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;

    public Tournament createTournament(CreateTournamentRequest tournamentRequest) {
        User organizer = userRepository.findByHandle(tournamentRequest.getOrganizerHandle())
                .orElseThrow(() -> new RuntimeException("Organizer handle not found!"));
        Problem problem = problemRepository.findByCode(tournamentRequest.getProblemCode())
                .orElseThrow(() -> new RuntimeException("Problem code not found!"));

        Tournament tournament = Tournament.builder()
                .name(tournamentRequest.getName())
                .organizer(organizer)
                .problem(problem)
                .status(Tournament.TournamentStatus.UPCOMING)
                .createdAt(OffsetDateTime.now())
                .build();
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }
}
