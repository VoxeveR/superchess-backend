package com.voxever.super_chess.auth.service.auth;


import com.voxever.super_chess.auth.entity.User;
import com.voxever.super_chess.auth.exception.custom.auth.UserNotFoundException;
import com.voxever.super_chess.auth.repository.UserRepository;
import com.voxever.super_chess.auth.util.JwtUtils;
import org.springframework.stereotype.Service;


@Service
public class AuthContextService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthContextService(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public Long extractUserIdFromToken(String rawToken) {
        String cleaned = JwtUtils.cleanToken(rawToken);
        return jwtService.extractUserId(cleaned);
    }

    public User getUserFromToken(String rawToken) {
        Long userId = extractUserIdFromToken(rawToken);
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
