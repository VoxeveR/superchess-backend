package com.voxever.super_chess.chessengine.controller;

import com.voxever.super_chess.chessengine.dto.CreateLobbyRequestDto;
import com.voxever.super_chess.chessengine.dto.JoinLobbyRequestDto;
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

    @MessageMapping("/createLobby")
    @SendToUser("/lobby/lobbyCode")
    public String createLobby(@Payload CreateLobbyRequestDto request, Principal principal) {
        return lobbyService.createLobby(request, principal);
    }

    @MessageMapping("/joinLobby")
    @SendToUser("/lobby/lobbyCode")
    public String joinGame(@Payload JoinLobbyRequestDto request, Principal principal) {
        return lobbyService.joinLobby(request, principal);
    }

    //TODO: implement
    public String leaveLobby(){
        return "";
    }

    //TODO: implement
    public String getLobbies(){
        return "";
    }

}
