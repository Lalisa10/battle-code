package com.example.battlecode_be.service;

import com.example.battlecode_be.dto.Create1v1MatchRequest;
import com.example.battlecode_be.model.*;
import com.example.battlecode_be.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchParticipantRepository participantRepository;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    private final TournamentRepository tournamentRepository;

    @Transactional
    public Match create1v1Match(Create1v1MatchRequest req) {

        Problem problem = problemRepository.findByCode(req.getProblemCode())
                .orElseThrow(() -> new IllegalArgumentException("Problem not found"));

        Submission s1 = submissionRepository.findById(req.getSubmission1Id())
                .orElseThrow(() -> new IllegalArgumentException("Submission 1 not found"));

        Submission s2 = submissionRepository.findById(req.getSubmission2Id())
                .orElseThrow(() -> new IllegalArgumentException("Submission 2 not found"));

        Tournament tournament = null;
        if (req.getTournamentId() != null) {
            tournament = tournamentRepository.findById(req.getTournamentId())
                    .orElseThrow(() -> new IllegalArgumentException("Tournament not found"));
        }

        Match match = matchRepository.save(
                Match.builder()
                        .problem(problem)
                        .tournament(tournament)
                        .status("PENDING")
                        .createdAt(OffsetDateTime.now())
                        .build()
        );

        participantRepository.save(
                MatchParticipant.builder()
                        .id(new MatchParticipantId(match.getId(), s1.getId()))
                        .match(match)
                        .submission(s1)
                        .slot((short) 1)
                        .build()
        );

        participantRepository.save(
                MatchParticipant.builder()
                        .id(new MatchParticipantId(match.getId(), s2.getId()))
                        .match(match)
                        .submission(s2)
                        .slot((short) 2)
                        .build()
        );

        return match;
    }
}

