package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;

public class Rook extends Piece {

    public Rook(Position position, Color color) {
        this.position = position;
        this.color = color;
        this.name="ROOK";
    }

    @Override
    public boolean isValidMovePattern(Move move) {
        return move.deltaX() == 0 || move.deltaY() == 0;
    }

}
