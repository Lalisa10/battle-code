package com.example.battlecode_be.repository;

import com.example.battlecode_be.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}

