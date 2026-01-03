package com.example.battlecode_be.repository;

import com.example.battlecode_be.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
