package com.voxever.super_chess.chessengine.service;

import com.voxever.super_chess.chessengine.ChessGame;
import com.voxever.super_chess.chessengine.LobbyStatus;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Player;
import com.voxever.super_chess.chessengine.entity.Lobby;
import com.voxever.super_chess.chessengine.exception.IllegalMoveException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChessEngineService {
    private final Map<String, ChessGame> activeGames = new ConcurrentHashMap<>();
    private final LobbyService lobbyService;

    public ChessEngineService(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    public ChessGame startGame(String lobbyCode) {
        System.out.println("STARTING GAME");

        Lobby lobby = lobbyService.getLobbyByCode(lobbyCode);

        if (lobby.getLobbyStatus() != LobbyStatus.PLAYING) {
            throw new IllegalStateException("Lobby is not ready to start a game.");
        }

        Player host = new Player(lobby.getHost().getUsername(), lobby.getHostColor());
        Player guest = new Player(lobby.getGuest().getUsername(), lobby.getGuestColor());

        ChessGame game = new ChessGame(
                host,
                guest,
                LobbyStatus.PLAYING
        );

        game.startGame(host.getNickname(), guest.getNickname());

        activeGames.put(lobby.getLobbyCode(), game);
        System.out.println("Created game" + lobby.getLobbyCode());
        return game;
    }

    public void makeMove(String lobbyCode, Move move) throws IllegalMoveException {
        ChessGame game = activeGames.get(lobbyCode);
        if (game == null) {
            throw new IllegalArgumentException("No active game for lobby code " + lobbyCode);
        }

        game.makeMove(move);
    }

    public ChessGame getGame(String lobbyCode) {
        if (lobbyCode == null) {
            throw new IllegalArgumentException("lobbyCode cannot be null");
        }

        activeGames.forEach((key, value) -> System.out.println(key + key)); // cleaner printing

        return activeGames.get(lobbyCode);
    }

    public void endGame(String lobbyCode) {
        activeGames.remove(lobbyCode);
    }

}
