package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;

public class Queen extends Piece {

    public Queen(Position position, Color color){
        this.position = position;
        this.color = color;
        this.name="QUEEN";
    }

    @Override
    public boolean isValidMovePattern(Move move) {
        int dx = move.deltaX();
        int dy = move.deltaY();

        if(Math.abs(dx) == Math.abs(dy)){
            return true;
        }

        if(dx == 0 || dy == 0){
            return true;
        }

        return false;
    }
}
