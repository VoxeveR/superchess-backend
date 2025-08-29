package com.voxever.super_chess.chessengine;


import com.voxever.super_chess.chessengine.pieces.Piece;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class ChessGameLobby {
    private Board board;

    private String roomCode;
    private Player playerWhite;
    private Player playerBlack;
    private Color currentTurn;
    private LobbyStatus lobbyStatus;
    private List<Move> moveHistory;

    public ChessGameLobby(String roomCode, Player host, LobbyStatus lobbyStatus){
        this.playerWhite = host;
        this.roomCode = roomCode;
        this.lobbyStatus = lobbyStatus;
    }

    public void startGame(String nicknameWhite, String nicknameBlack, String roomCode){
        this.board = new Board();
        this.playerWhite = new Player(nicknameWhite, Color.WHITE);
        this.playerBlack = new Player(nicknameBlack, Color.BLACK);
        this.currentTurn = Color.WHITE;
        this.moveHistory = new ArrayList<>();
    }

    public Boolean makeMove(Move move) {
        Piece piece = board.getPieceAt(move.getFrom().getX(),move.getFrom().getY());

        if(piece == null || piece.getColor() != currentTurn){
            return false;
        }

        if(!piece.isMoveLegal(move, board)){
            return false;
        }

        Piece capturePiece = board.getPieceAt(move.getTo().getX(), move.getTo().getY());

        capturePiece = null;

        //check if king under attack

        moveHistory.add(move);
        switchTurn();

        return true;
    }

    private void switchTurn(){
        if(currentTurn == Color.WHITE) currentTurn = Color.BLACK;
        else currentTurn = Color.WHITE;
    }

    private void capturePiece(){

    }
    private Boolean isCheckmate(){
        return false;
    }

    private Boolean isMate() {return false;}

    private Boolean isStalemate(){
        return true;
    }
}