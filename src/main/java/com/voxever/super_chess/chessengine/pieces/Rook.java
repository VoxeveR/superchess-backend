package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;

public class Rook extends Piece {

    public Rook(Position position, Color color){
        this.position = position;
        this.color = color;
    }

    @Override
    public Boolean isMoveLegal(Move move, Board board) {
        return null;
    }
}
