package com.voxever.super_chess.auth.repository;

import com.voxever.super_chess.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    List<RefreshToken> findAllByPrefix(String tokenPrefix);
}