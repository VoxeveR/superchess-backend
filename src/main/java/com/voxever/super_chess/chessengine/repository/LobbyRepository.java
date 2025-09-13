package com.voxever.super_chess.chessengine.repository;

import com.voxever.super_chess.auth.entity.User;
import com.voxever.super_chess.chessengine.entity.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LobbyRepository extends JpaRepository<Lobby, Long> {
    Boolean existsByHostOrGuest(User host, User guest);

    Optional<Lobby> findByLobbyCode(String lobbyCode);
}