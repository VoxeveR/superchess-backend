package com.voxever.super_chess.chessengine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PossibleMovesResponseDto {
    private String lobbyCode;
    private int fromRow;
    private int fromCol;
    private List<MoveDto> possibleMoves;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class MoveDto {
        private int toRow;
        private int toCol;
    }
}
