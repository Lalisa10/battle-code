package com.example.battlecode_be.repository;


import com.example.battlecode_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByHandle(String handle);

    boolean existsByEmail(String email);

    boolean existsByHandle(String handle);
}
