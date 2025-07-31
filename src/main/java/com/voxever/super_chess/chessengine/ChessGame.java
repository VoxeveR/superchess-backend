package com.voxever.super_chess.chessengine;


import com.voxever.super_chess.chessengine.pieces.Piece;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChessGame {
    private Board board;

    private String roomCode;
    private Player playerWhite;
    private Player playerBlack;
    private Color currentTurn;
    private List<Move> moveHistory;

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