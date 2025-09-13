package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;

public class Pawn extends Piece {

    private boolean isFirstMove = true;

    public Pawn(Position position, Color color){
        this.position = position;
        this.color = color;
        this.name="PAWN";
    }

    @Override
    public boolean isValidMovePattern(Move move) {
        int dx = move.deltaX();
        int dy = move.deltaY();

        // Ensure pawn moves in correct forward direction
        int correctDirection = (this.color == Color.WHITE) ? 1 : -1;
        int moveDirection = (dx < 0) ? 1 : -1;
        if (moveDirection != correctDirection) return false;

        // Forward moves
        if (dy == 0) {
            if (Math.abs(dx) == 1) return true;
            if (Math.abs(dx) == 2 && isFirstMove) return true;
        }

        // Diagonal capture
        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) return true;

        return false;
    }

    @Override
    public boolean isMoveLegal(Move move, Board board) {

        if(!this.isValidMovePattern(move)) return false;
//
//
//        //TODO: UPDATE ISFIRSTMOVE!!!
//        // move forward
//        if(dy == 0){
//            if(Math.abs(dx) == 1 || Math.abs(dx) == 2 && )
//        } else { // attack
//
//        }
//
//        //diagonal capture
//        if()

        return true;
    }
}
