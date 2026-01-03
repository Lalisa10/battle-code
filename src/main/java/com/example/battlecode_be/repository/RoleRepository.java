package com.example.battlecode_be.repository;

import com.example.battlecode_be.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Short> {

    Optional<Role> findByName(Role.RoleName name);
}
