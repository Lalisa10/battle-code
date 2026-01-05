package com.example.battlecode_be.repository;

import com.example.battlecode_be.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
