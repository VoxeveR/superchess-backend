package com.voxever.super_chess.controller.websocket;

import com.voxever.super_chess.service.ChessGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerConnectionWebSocketController {

    private ChessGameService chessGameService;

    @Autowired
    public PlayerConnectionWebSocketController(ChessGameService chessGameService){
        this.chessGameService = chessGameService;
    }

    @MessageMapping("/createRoom")
    @SendToUser("/game/roomCode")
    public String createGame(String message) {
        return chessGameService.createGame(message);
    }

    @MessageMapping("/joinRoom")
    @SendToUser("/game/roomCode")
    public String joinGame(String message) {
        return chessGameService.joinGame(message);
    }
}
