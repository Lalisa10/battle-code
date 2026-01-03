package com.example.battlecode_be.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---- Problem ---- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    /* ---- Tournament (optional) ---- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    /* ---- Status ---- */
    @Column(nullable = false, length = 20)
    private String status; // PENDING, RUNNING, FINISHED

    /* ---- Winner ---- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_submission_id")
    private Submission winnerSubmission;

    /* ---- Replay ---- */
    private String eventsUrl;

    @Column(nullable = false)
    private OffsetDateTime createdAt;
}
