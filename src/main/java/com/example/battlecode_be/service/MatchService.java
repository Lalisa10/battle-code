package com.example.battlecode_be.service;

import com.example.battlecode_be.dto.Create1v1MatchRequest;
import com.example.battlecode_be.dto.MatchQueueMessage;
import com.example.battlecode_be.dto.MatchResponse;
import com.example.battlecode_be.dto.UpdateMatchRequest;
import com.example.battlecode_be.dto.UserResponse;
import com.example.battlecode_be.model.*;
import com.example.battlecode_be.queue.MatchQueueProducer;
import com.example.battlecode_be.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchParticipantRepository participantRepository;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchQueueProducer matchQueueProducer;
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
                        .status(Match.MatchStatus.PENDING)
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

    @Transactional
    public void startMatch(Long matchId) {

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));
        List<MatchParticipant> matchParticipants = participantRepository.findByMatchId(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match participant not found"));

//        if (!Match.MatchStatus.PENDING.equals(match.getStatus())) {
//            throw new IllegalStateException("Match is not in PENDING state");
//        }

        match.setStatus(Match.MatchStatus.RUNNING);
        matchRepository.save(match);

        List<MatchQueueMessage.SubmissionInfo> subs =
                matchParticipants.stream()
                        .sorted(Comparator.comparing(MatchParticipant::getSlot))
                        .map(mp -> MatchQueueMessage.SubmissionInfo.builder()
                                .submissionId(mp.getSubmission().getId())
                                .slot(mp.getSlot())
                                .codeUrl(mp.getSubmission().getCodeUrl())
                                .build()
                        )
                        .toList();

        MatchQueueMessage msg = MatchQueueMessage.builder()
                .matchId(match.getId())
                .problem(
                        MatchQueueMessage.ProblemInfo.builder()
                                .engine(match.getProblem().getEngineType())
                                //.boardSize(match.getProblem().getBoardSize())
                                //.winCondition(match.getProblem().getWinCondition())
                                .boardSize(30)
                                .winCondition(5)
                                .timeLimitMs(match.getProblem().getTimeLimitMs())
                                .build()
                )
                .submissions(subs)
                .build();

        matchQueueProducer.enqueue(msg);

    }

    @Transactional
    public MatchResponse getMatchResponse(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        return toMatchResponse(match);
    }

    @Transactional
    public MatchResponse updateMatch(Long matchId, UpdateMatchRequest request) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        if (request.getStatus() != null) {
            String normalizedStatus = request.getStatus().toUpperCase(Locale.ROOT);
            match.setStatus(Match.MatchStatus.valueOf(normalizedStatus));
        }

        if (request.getWinnerSubmissionId() != null) {
            Submission winnerSubmission = submissionRepository.findById(request.getWinnerSubmissionId())
                    .orElseThrow(() -> new IllegalArgumentException("Winner submission not found"));
            match.setWinnerSubmission(winnerSubmission);
        }

        if (request.getEventsUrl() != null) {
            match.setEventsUrl(request.getEventsUrl());
        }

        return toMatchResponse(matchRepository.save(match));
    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .handle(user.getHandle())
                .email(user.getEmail())
                .isActive(user.isActive())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(role -> role.getName().name())
                                .collect(Collectors.toSet())
                )
                .build();
    }

    private MatchResponse toMatchResponse(Match match) {
        Submission winnerSubmission = match.getWinnerSubmission();
        UserResponse winner = null;
        if (winnerSubmission != null) {
            winner = toUserResponse(winnerSubmission.getUser());
        }

        Long tournamentId = null;
        if (match.getTournament() != null) {
            tournamentId = match.getTournament().getId();
        }

        return MatchResponse.builder()
                .matchId(match.getId())
                .problemCode(match.getProblem().getId())
                .tournamentId(tournamentId)
                .winner(winner)
                .status(String.valueOf(match.getStatus()))
                .eventsUrl(match.getEventsUrl())
                .build();
    }
}
