package com.voxever.super_chess.chessengine;

import com.voxever.super_chess.chessengine.pieces.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    public static final int COLUMNS = 8;
    public static final int ROWS = 8;

    Piece[][] board = new Piece[ROWS][COLUMNS];

    public Board() {
        this.board = setupDefaultBoardState();
    }

    public Board(Piece[][] initialState) {
        this.board = initialState;
    }

    private Piece[][] setupDefaultBoardState(){
        Piece[][] defaultBoard = new Piece[ROWS][COLUMNS];

        for(int i = 0; i < 8; i++){
            defaultBoard[1][i] = new Pawn(new Position(1, i), Color.BLACK);
            defaultBoard[6][i] = new Pawn(new Position(6, i), Color.WHITE);
        }

        defaultBoard[0][0] = new Rook(new Position(0, 0), Color.BLACK);
        defaultBoard[0][7] = new Rook(new Position(0, 7), Color.BLACK);
        defaultBoard[7][0] = new Rook(new Position(7, 0), Color.WHITE);
        defaultBoard[7][7] = new Rook(new Position(7, 7), Color.WHITE);

        defaultBoard[0][1] = new Knight(new Position(0, 1), Color.BLACK);
        defaultBoard[0][6] = new Knight(new Position(0, 6), Color.BLACK);
        defaultBoard[7][1] = new Knight(new Position(7, 1), Color.WHITE);
        defaultBoard[7][6] = new Knight(new Position(7, 6), Color.WHITE);

        defaultBoard[0][2] = new Bishop(new Position(0, 2), Color.BLACK);
        defaultBoard[0][5] = new Bishop(new Position(0, 5), Color.BLACK);
        defaultBoard[7][2] = new Bishop(new Position(7, 2), Color.WHITE);
        defaultBoard[7][5] = new Bishop(new Position(7, 5), Color.WHITE);

        defaultBoard[0][3] = new Queen(new Position(0, 3), Color.BLACK);
        defaultBoard[7][3] = new Queen(new Position(7, 3), Color.WHITE);

        defaultBoard[0][4] = new King(new Position(0, 4), Color.BLACK);
        defaultBoard[7][4] = new King(new Position(7, 4), Color.WHITE);

        for(Piece[] piece : board){
            for(Piece piece2 : piece){
                if(piece2 !=null) System.out.println(piece2.getName() + piece2.getPosition().getCol() + piece2.getPosition().getRow());
            }
        }
        return defaultBoard;
    }

    public Piece getPieceAt(int row, int column) {
        return board[row][column];
    }

    public boolean isPathClear(Move move){
        int fromX = move.getFromRow();
        int fromY = move.getFromCol();

        int diffX = move.deltaX();
        int diffY = move.deltaY();

        int stepX = Integer.signum(diffX);
        int stepY = Integer.signum(diffY);

        int stepCount = Math.max(Math.abs(diffX), Math.abs(diffY));

        for(int i = 1; i < stepCount; i++){
            if(this.board[fromX + i * stepX][fromY + i * stepY] != null) return false;
        }

        return true;
    }
}
