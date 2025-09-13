package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;
import com.voxever.super_chess.chessengine.Color;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Piece {
    public String name;
    public Color color;
    public Position position;

    public Piece(){
    }

    public abstract boolean isValidMovePattern(Move move);

    public boolean isMoveLegal(Move move, Board board) {
        if (!isValidMovePattern(move)) return false;

        if (!(this instanceof Knight) && !board.isPathClear(move)) return false;

        Piece target = board.getPieceAt(move.getToRow(), move.getToCol());
        return target == null || target.getColor() != this.color;
    }

    //abstract void getPossibleMoves();
    //abstract void getLegalMoves();
}
