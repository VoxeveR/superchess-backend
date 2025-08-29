package com.voxever.super_chess.chessengine.controller;

import com.voxever.super_chess.chessengine.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class LobbyController {

    private final LobbyService lobbyService;

    @Autowired
    public LobbyController(LobbyService lobbyService){
        this.lobbyService = lobbyService;
    }

    @MessageMapping("/createRoom")
    @SendToUser("/game/roomCode")
    public String createLobby(@Payload String message, Principal principal) {
        return lobbyService.createLobby(message, principal);
    }

    @MessageMapping("/joinRoom")
    @SendToUser("/game/roomCode")
    public String joinGame(String message) {
        return lobbyService.joinLobby();
    }
}
