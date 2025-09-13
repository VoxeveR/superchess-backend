package com.voxever.super_chess.chessengine.dto;

import com.voxever.super_chess.chessengine.Position;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveRequestDto {
    String lobbyCode;
    Position from;
    Position to;
}
