package com.voxever.super_chess.chessengine.mapper;

import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.dto.LobbyDto;
import com.voxever.super_chess.chessengine.dto.PlayerDto;
import com.voxever.super_chess.chessengine.entity.Lobby;
import org.springframework.stereotype.Component;

@Component
public class LobbyMapper {

    public LobbyDto toLobbyDto(Lobby lobby){

        return new LobbyDto(
                lobby.getLobbyCode(),
                lobby.getHost() != null ? new PlayerDto(lobby.getHost().getUsername(), Color.WHITE) : null,
                lobby.getGuest() != null ? new PlayerDto(lobby.getGuest().getUsername(), Color.BLACK) : null,
                lobby.getLobbyStatus().toString()
        );
    }
}
