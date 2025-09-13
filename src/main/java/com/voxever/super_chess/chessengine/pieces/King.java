package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;

public class King extends Piece {

    public King(Position position, Color color){
        this.position = position;
        this.color = color;
        this.name="KING";
    }

    @Override
    public boolean isValidMovePattern(Move move) {
        int dx = Math.abs(move.deltaX());
        int dy = Math.abs(move.deltaY());

        return (dx <= 1 && dy <= 1) && (dx + dy > 0);
    }
}
