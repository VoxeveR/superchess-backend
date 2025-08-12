package com.voxever.super_chess.service;

import com.voxever.super_chess.util.RoomCodeGenerator;
import com.voxever.super_chess.chessengine.ChessGame;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChessGameService {


    private final Set<String> connectedNicknames = Collections.synchronizedSet(new HashSet<>());
    private final Set<String> availableRooms = Collections.synchronizedSet(new HashSet<>());

    private final Map<String, ChessGame> gameByRoomCode = Collections.synchronizedMap(new HashMap<>());


    public String createGame(String message){
        System.out.println(">>> Backend received nickname: " + message);
        String roomCode = RoomCodeGenerator.generateCode(6);

        connectedNicknames.add(message);
        availableRooms.add(roomCode);

        ChessGame newGame = new ChessGame();
        //newGame.setPlayerWhite(message);
        gameByRoomCode.put(roomCode, newGame);


        System.out.println("Player: " + message + " created a room!");

        return roomCode;
    }

    public String joinGame(String message){
        System.out.println(">>> Backend received roomCode: " + message);
        String roomCode = message;

        if(availableRooms.contains(roomCode)){
            availableRooms.remove(roomCode);
            connectedNicknames.add(message);
            System.out.println("Player: " + message + " joined a room!");

            return roomCode;
        }

        return null;
    }

}
