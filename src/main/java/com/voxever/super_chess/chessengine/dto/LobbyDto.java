package com.voxever.super_chess.chessengine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyDto {
    private String lobbyCode;
    private PlayerDto host;
    private PlayerDto guest;
    private String status;
}