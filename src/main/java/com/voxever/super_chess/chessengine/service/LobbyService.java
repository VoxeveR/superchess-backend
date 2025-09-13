package com.voxever.super_chess.chessengine.service;

import com.voxever.super_chess.auth.entity.User;
import com.voxever.super_chess.auth.service.UserService;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.LobbyStatus;
import com.voxever.super_chess.chessengine.dto.*;
import com.voxever.super_chess.chessengine.entity.Lobby;
import com.voxever.super_chess.chessengine.mapper.LobbyMapper;
import com.voxever.super_chess.chessengine.repository.LobbyRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
public class LobbyService {
    private final LobbyRepository lobbyRepository;
    private final UserService userService;
    private final LobbyMapper lobbyMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChessEngineService chessEngineService;

    public LobbyService(LobbyRepository lobbyRepository, UserService userService, LobbyMapper lobbyMapper, SimpMessagingTemplate messagingTemplate, @Lazy ChessEngineService chessEngineService) {
        this.lobbyRepository = lobbyRepository;
        this.userService = userService;
        this.lobbyMapper = lobbyMapper;
        this.messagingTemplate = messagingTemplate;
        this.chessEngineService = chessEngineService;
    }

    @Transactional
    public String createLobby(CreateLobbyRequestDto message, Principal principal){
        System.out.println(">>> Backend received  createLobby request: " + message);

        User host = userService.findByUsername(principal.getName());

        if(isPlayerInLobby(host)){
            throw new IllegalStateException("Player is already in a lobby!");
        }

        String lobbyCode = generateUniqueLobbyCode();

        Lobby lobby = Lobby.builder()
                .lobbyCode(lobbyCode)
                .host(host)
                .lobbyStatus(LobbyStatus.WAITING)
                .hostColor(Color.WHITE)
                .guestColor(Color.BLACK)
                .build();

        lobbyRepository.save(lobby);

        return lobbyCode;
    }

    private Boolean isPlayerInLobby(User user){
        return lobbyRepository.existsByHostOrGuest(user, user);
    }

    private String generateUniqueLobbyCode(){
        String generatedCode;
        do {
            generatedCode = generateCode(8);
        } while(lobbyRepository.findByLobbyCode(generatedCode).isPresent());
        return generatedCode;
    }

    private String generateCode(Integer codeLength){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase().substring(0, codeLength - 1);
    }

    @Transactional
    public String joinLobby(JoinLobbyRequestDto request, Principal principal){
        System.out.println(">>> Backend received joinLobby request: " + request);

        String lobbyCode = request.getLobbyCode();
        System.out.println(request.getLobbyCode() + "kodzik");
        User guest = userService.findByUsername(principal.getName());
        Lobby lobby = getLobbyByCode(lobbyCode);

        if(isPlayerInLobby(guest)){
            throw new IllegalStateException("Player is already in a lobby!");
        }

        lobby.setGuest(guest);
        lobby.setLobbyStatus(LobbyStatus.PLAYING);
        lobbyRepository.save(lobby);

        chessEngineService.startGame(lobbyCode);

        messagingTemplate.convertAndSend("/topic/lobby/" + lobby.getLobbyCode(), lobbyMapper.toLobbyDto(lobby));

        return lobbyCode;
    }

    //TODO: IMPLEMENT
    public String leaveLobby(){
        return "";
    }

    //TODO: IMPLEMENT
    public String getLobbies(){
        return "";
    }

    public Lobby getLobbyByCode(String lobbyCode) throws IllegalArgumentException {
        return lobbyRepository.findByLobbyCode(lobbyCode)
                .orElseThrow(() -> new IllegalArgumentException("Lobby not found"));
    }
}