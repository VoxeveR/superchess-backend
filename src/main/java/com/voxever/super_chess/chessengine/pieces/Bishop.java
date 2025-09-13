package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;

public class Bishop extends Piece {

    public Bishop(Position position, Color color){
        this.position = position;
        this.color = color;
        this.name="BISHOP";
    }

    @Override
    public boolean isValidMovePattern(Move move) {
        return Math.abs(move.deltaX()) == Math.abs(move.deltaY());
    }
}
