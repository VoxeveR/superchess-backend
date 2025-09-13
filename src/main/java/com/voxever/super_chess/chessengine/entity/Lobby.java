package com.voxever.super_chess.chessengine.entity;

import com.voxever.super_chess.auth.entity.User;
import com.voxever.super_chess.chessengine.Color;
import com.voxever.super_chess.chessengine.LobbyStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="lobbies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lobbyId;

    private String lobbyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    private User host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private User guest;

    @Enumerated(EnumType.STRING)
    private Color hostColor;

    @Enumerated(EnumType.STRING)
    private Color guestColor;

    @Enumerated(EnumType.STRING)
    private LobbyStatus lobbyStatus;
}
