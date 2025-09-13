package com.voxever.super_chess.chessengine;

import com.voxever.super_chess.chessengine.pieces.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Move {
    private Position from;
    private Position to;

    public int getFromRow() { return from.getRow(); }
    public int getFromCol() { return from.getCol(); }
    public int getToRow()   { return to.getRow(); }
    public int getToCol()   { return to.getCol(); }

    public int deltaX() { return getToRow() - getFromRow(); }
    public int deltaY() { return getToCol() - getFromCol(); }
}
