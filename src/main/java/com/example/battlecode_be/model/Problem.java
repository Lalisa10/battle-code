package com.example.battlecode_be.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "problems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String engineType;

    @Column(nullable = false)
    private Integer timeLimitMs;

    @Column(nullable = false)
    private Integer memoryLimitMb;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private OffsetDateTime createdAt;
}
