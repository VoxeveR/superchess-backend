package com.voxever.super_chess.controller;

import com.voxever.super_chess.service.ChessGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerConnectionController {

    private ChessGameService chessGameService;

    @Autowired
    public PlayerConnectionController(ChessGameService chessGameService){
        this.chessGameService = chessGameService;
    }

    @MessageMapping("/createRoom")
    @SendToUser("/game/roomCode")
    public String createGame(String message) {
        return chessGameService.createGame(message);
    }
}
