package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;

public class Knight extends Piece {

    public Knight(Position position, Color color) {
        this.position = position;
        this.color = color;
        this.name="KNIGHT";
    }

    @Override
    public boolean isValidMovePattern(Move move) {
        int dx = Math.abs(move.deltaX());
        int dy = Math.abs(move.deltaY());

        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
