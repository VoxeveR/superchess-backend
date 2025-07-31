package com.voxever.super_chess.chessengine.pieces;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.Position;

public class Knight extends Piece {

    public Knight(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    @Override
    public Boolean isMoveLegal(Move move, Board board) {
        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getX();

        //check if move is in straight line
        if(fromX != toX && fromY != toY){
            return false;
        }

        // check if there are no figures on path (without last square)
        if(fromX == toX){
            int step = (fromY < toY) ? 1 : -1;
            for(int i = fromY + step; i != toY; i += step){
                if(board.getPieceAt(fromX, i) != null) return false;
            }
        } else {
            int step = (fromX < toX) ? 1 : -1;
            for(int i = fromX + step; i != toX; i += step){
                if(board.getPieceAt(i, fromY) != null) return false;
            }
        }

        Piece target = board.getPieceAt(toX, toY);
        if(target.getColor() == this.color) return false;

        return true;
    }
}
