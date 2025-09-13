package com.voxever.super_chess.chessengine.controller;

import com.voxever.super_chess.chessengine.dto.*;
import com.voxever.super_chess.chessengine.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @MessageMapping("/game/move")
    public void makeMove(@Payload MoveRequestDto request, Principal principal) {
        gameService.makeMove(request, principal);
    }

    @MessageMapping("/game/getPossibleMoves")
    @SendToUser("/queue/possibleMoves")
    public PossibleMovesResponseDto getPossibleMoves(@Payload PossibleMovesRequestDto request, Principal principal) {
        return gameService.getPossibleMoves(request, principal);
    }

    @MessageMapping("/lobby/getState")
    @SendToUser("/queue/gameState")
    public GameStateDto getGameState(@Payload GetGameStateRequestDto request, Principal principal) {
        System.out.println("RECEIVED ON CONNECT");
        return gameService.getGameState(request, principal);
    }

}