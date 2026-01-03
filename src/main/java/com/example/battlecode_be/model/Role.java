package com.example.battlecode_be.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false, unique = true, length = 32)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
        PLAYER,
        ORGANIZER,
        ADMIN
    }
}
