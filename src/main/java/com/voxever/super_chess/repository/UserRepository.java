package com.voxever.super_chess.repository;

import com.voxever.super_chess.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDisplayName(String username);

    Optional<User> findByEmail(String email);

}