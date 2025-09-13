package com.voxever.super_chess.chessengine.exception;

public class IllegalMoveException extends RuntimeException {
    public IllegalMoveException(String message){
        super(message);
    }
}
