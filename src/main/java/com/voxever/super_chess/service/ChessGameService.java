package com.voxever.super_chess.service;

import com.voxever.super_chess.RoomCodeGenerator;
import com.voxever.super_chess.chessengine.ChessGame;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ChessGameService {

    private ChessGame chessGame;
    private final Set<String> connectedNicknames = Collections.synchronizedSet(new HashSet<>());

    @Autowired
    public ChessGameService(ChessGame chessGame){
        this.chessGame = chessGame;
    }

    public String createGame(String message){
        System.out.println(">>> Backend received nickname: " + message); // <--- add this
        String roomCode = RoomCodeGenerator.generateCode(6);
        connectedNicknames.add(message);

        System.out.println("Player: " + message + " created a room!");


        return roomCode;
    }

}
