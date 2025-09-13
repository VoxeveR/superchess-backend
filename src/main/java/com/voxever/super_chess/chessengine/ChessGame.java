package com.voxever.super_chess.chessengine;


import com.voxever.super_chess.chessengine.exception.IllegalMoveException;
import com.voxever.super_chess.chessengine.pieces.Piece;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class ChessGame {
    private Board board;
    private Player host;
    private Player guest;
    private Color currentTurn;
    private LobbyStatus lobbyStatus;
    private List<Move> moveHistory;

    public ChessGame(Player host, Player guest, LobbyStatus lobbyStatus){
        this.host = host;
        this.guest = guest;
        this.lobbyStatus = lobbyStatus;
    }

    public void startGame(String nicknameWhite, String nicknameBlack){
        this.board = new Board();
        this.host = new Player(nicknameWhite, Color.WHITE);
        this.guest = new Player(nicknameBlack, Color.BLACK);
        this.currentTurn = Color.WHITE;
        this.moveHistory = new ArrayList<>();
    }

    public List<Move> getPossibleMoves(Piece piece) {
        List<Move> possibleMoves = new ArrayList<>();
        System.out.println("HAHAHAHAHA" + piece.getPosition().getRow() + " : " + piece.getPosition().getCol());
        if (piece == null) {
            System.out.println("Returning null");
            return possibleMoves;
        }

        int fromRow = piece.getPosition().getRow();
        int fromCol = piece.getPosition().getCol();

        // Loop over all squares
        for (int toRow = 0; toRow < Board.ROWS; toRow++) {
            for (int toCol = 0; toCol < Board.COLUMNS; toCol++) {
                Move move = new Move(piece.getPosition(), new Position(toRow, toCol));
                if (piece.isMoveLegal(move, board)) {
                    possibleMoves.add(move);
                }
            }
        }
        //TODO:REMOVE
        System.out.println("ALL POSSIBLE MOVES");
        for(Move element : possibleMoves){
            System.out.println(element.getToRow() + " " + element.getToCol());
        }
        return possibleMoves;
    }

    public void makeMove(Move move) throws IllegalMoveException {
        Piece piece = board.getPieceAt(move.getFrom().getRow(),move.getFrom().getCol());

        System.out.println("FROM: " + move.getFrom().row + ", " + move.getFrom().col + " TO " + move.getTo().row + ", " + move.getTo().col);

        if(piece == null){
            throw new IllegalMoveException("No piece at source position.");
        }

        if(piece.getColor() != currentTurn){
            throw new IllegalMoveException("It's not your turn.");
        }

        if(!piece.isMoveLegal(move, board)){
            throw new IllegalMoveException("Move is not legal!");
        }

        Piece capturePiece = board.getPieceAt(move.getTo().getRow(), move.getTo().getCol());

        capturePiece = null;

        //check if king under attack

        moveHistory.add(move);
        switchTurn();
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