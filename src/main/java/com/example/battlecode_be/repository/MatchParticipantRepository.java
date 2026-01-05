package com.example.battlecode_be.repository;

import com.example.battlecode_be.model.MatchParticipant;
import com.example.battlecode_be.model.MatchParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchParticipantRepository
        extends JpaRepository<MatchParticipant, MatchParticipantId> {

    List<MatchParticipant> findByMatchId(Long matchId);
}