package com.voxever.super_chess.exception.custom.auth;

public class CredentialsAlreadyTakenException extends RuntimeException {
    public CredentialsAlreadyTakenException(String message){
        super(message);
    }
}
