package com.voxever.super_chess.chessengine.dto;

import com.voxever.super_chess.chessengine.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private String username;
    private Color color;
}
