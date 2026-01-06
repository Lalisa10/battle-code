package com.example.battlecode_be.repository;

import com.example.battlecode_be.model.MatchParticipant;
import com.example.battlecode_be.model.MatchParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchParticipantRepository
        extends JpaRepository<MatchParticipant, MatchParticipantId> {

    Optional<List<MatchParticipant>> findByMatchId(Long matchId);

    List<MatchParticipant> findByMatchIdIn(List<Long> matchIds);
}
