package com.voxever.super_chess.chessengine.service;

import com.voxever.super_chess.chessengine.ChessGame;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.Move;
import com.voxever.super_chess.chessengine.dto.*;
import com.voxever.super_chess.chessengine.entity.Lobby;
import com.voxever.super_chess.chessengine.exception.IllegalMoveException;
import jakarta.transaction.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final LobbyService lobbyService;
    private final ChessEngineService chessEngineService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameService(LobbyService lobbyService, ChessEngineService chessEngineService, SimpMessagingTemplate messagingTemplate){
        this.lobbyService = lobbyService;
        this.chessEngineService = chessEngineService;
        this.messagingTemplate = messagingTemplate;
    }

    //TODO: IMPLEMENT XD
    @Transactional
    public void makeMove(MoveRequestDto request, Principal principal){
        String lobbyCode = request.getLobbyCode();
        ChessGame game = chessEngineService.getGame(lobbyCode);

        Move move = new Move(request.getFrom(), request.getTo());

        try {
            game.makeMove(move);

            MoveResponseDto response = new MoveResponseDto(
                    request.getLobbyCode(),
                    game.getBoard()
            );

            messagingTemplate.convertAndSend(
                    "/topic/game/" + request.getLobbyCode() + "/boardUpdates",
                    response
            );

        } catch (IllegalMoveException e) {
            messagingTemplate.convertAndSendToUser(
                    principal.getName(),
                    "/queue/errors",
                    e.getMessage()
            );
        }
    }

    //TODO: REFACTOR
    @Transactional
    public GameStateDto getGameState(GetGameStateRequestDto request, Principal principal) {
        String lobbyCode = request.getLobbyCode();
        Lobby lobby = lobbyService.getLobbyByCode(lobbyCode);

        String username = principal.getName();
        Color playerColor = null;

        if(lobby.getGuest() != null && lobby.getHost().getUsername().equals(username)){
            playerColor = Color.WHITE;
        } else if (lobby.getGuest() != null && lobby.getGuest().getUsername().equals(username)) {
            playerColor = Color.BLACK;
        }

        return new GameStateDto(lobby, playerColor);
    }

    public PossibleMovesResponseDto getPossibleMoves(PossibleMovesRequestDto request, Principal principal) {
        String lobbyCode = request.getLobbyCode();
        ChessGame game = chessEngineService.getGame(lobbyCode);

        var piece = game.getBoard().getPieceAt(request.getRow(), request.getCol());
        System.out.println(piece.getName());


        List<PossibleMovesResponseDto.MoveDto> moves;

        if (piece != null) {
            moves = game.getPossibleMoves(piece)
                    .stream()
                    .map(m -> new PossibleMovesResponseDto.MoveDto(
                            m.getTo().getRow(),
                            m.getTo().getCol()
                    ))
                    .collect(Collectors.toList());
        } else {
            moves = List.of();
        }

        return new PossibleMovesResponseDto(
                lobbyCode,
                request.getRow(),
                request.getCol(),
                moves
        );
    }
}
