package com.example.battlecode_be.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "match_participants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchParticipant {

    @EmbeddedId
    private MatchParticipantId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("matchId")
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("submissionId")
    @JoinColumn(name = "submission_id")
    private Submission submission;

    @Column(nullable = false)
    private Short slot; // 1, 2
}

