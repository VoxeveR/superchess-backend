package com.voxever.super_chess.chessengine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PossibleMovesRequestDto {
    private String lobbyCode;
    private int row;
    private int col;
}
