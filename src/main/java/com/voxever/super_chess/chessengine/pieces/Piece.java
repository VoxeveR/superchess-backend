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

    public abstract Boolean isMoveLegal(Move move, Board board);
    //abstract void getPossibleMoves();
    //abstract void getLegalMoves();
}
