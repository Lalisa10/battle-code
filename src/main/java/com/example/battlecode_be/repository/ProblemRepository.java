package com.example.battlecode_be.repository;

import com.example.battlecode_be.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Optional<Problem> findByCode(String code);
    boolean existsByCode(String code);
}
