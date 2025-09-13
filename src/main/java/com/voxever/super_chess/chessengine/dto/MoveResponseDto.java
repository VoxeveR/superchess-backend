package com.voxever.super_chess.chessengine.dto;

import com.voxever.super_chess.chessengine.Board;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MoveResponseDto {
    private String lobbyCode;
    private Board board;
}
