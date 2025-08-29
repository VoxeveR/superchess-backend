package com.voxever.super_chess.chessengine.service;

import com.voxever.super_chess.chessengine.ChessGameLobby;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.LobbyStatus;
import com.voxever.super_chess.chessengine.Player;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LobbyService {
    private Map<String, ChessGameLobby> lobbies = new ConcurrentHashMap<>();

    public String createLobby(String message, Principal principal){
        System.out.println(">>> Backend received nickname: " + message);
        String roomCode = generateLobbyCode();

        ChessGameLobby chessGameLobby =
                new ChessGameLobby(roomCode, new Player(principal.getName(), Color.WHITE), LobbyStatus.WAITING);

        lobbies.put(roomCode, chessGameLobby);

        return roomCode;
    }

    public String joinLobby(){
        return "";
    }

    private String generateLobbyCode(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase().substring(0,7);
    }
}
