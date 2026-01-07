package com.example.battlecode_be.repository;

import com.example.battlecode_be.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByProblem_Id(Long id);
}
