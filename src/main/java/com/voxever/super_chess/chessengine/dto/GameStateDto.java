package com.voxever.super_chess.chessengine.dto;

import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.entity.Lobby;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameStateDto {
    private String lobbyId;
    private String status;
    private String currentTurn;
    private PlayerDto blackPlayer;
    private PlayerDto whitePlayer;
    private Color currentPlayerColor;


    public GameStateDto(Lobby lobby, Color currentPlayerColor) {
        this.lobbyId = lobby.getLobbyCode();
        this.status = lobby.getLobbyStatus().toString();
        this.whitePlayer = lobby.getHost() != null
                ? new PlayerDto(lobby.getHost().getUsername(), lobby.getHostColor())
                : null;
        this.blackPlayer = lobby.getGuest() != null
                ? new PlayerDto(lobby.getGuest().getUsername(), lobby.getGuestColor())
                : null;
        this.currentPlayerColor = currentPlayerColor;
    }
}
