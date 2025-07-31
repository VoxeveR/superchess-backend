package com.voxever.super_chess.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class PlayerConnectionService {

    private final Set<String> connectedNicknames = Collections.synchronizedSet(new HashSet<>());

    public void addPlayer(String nickname) {
        connectedNicknames.add(nickname);
    }

    public Set<String> getConnectedPlayers() {
        return connectedNicknames;
    }
}
