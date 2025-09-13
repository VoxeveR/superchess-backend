package com.voxever.super_chess.chessengine.dto;

import com.voxever.super_chess.chessengine.Board;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Position;
import com.voxever.super_chess.chessengine.pieces.Piece;
import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MoveResponseDto {
    private String lobbyCode;
    private Board board;
}
