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
    private Piece movedPiece;
    private Piece capturedPiece;
}
