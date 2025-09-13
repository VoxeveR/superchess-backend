package com.voxever.super_chess.chessengine.config;

import com.voxever.super_chess.auth.entity.User;
import com.voxever.super_chess.auth.exception.custom.auth.UserNotFoundException;
import com.voxever.super_chess.auth.repository.UserRepository;
import com.voxever.super_chess.auth.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class WebSocketAuthenticatorService {

    public final JwtService jwtService;
    public final UserRepository userRepository;

    @Autowired
    public WebSocketAuthenticatorService(JwtService jwtService, UserRepository userRepository){
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public UsernamePasswordAuthenticationToken getAuthenticationOrFail(String jwtToken) {
        if (jwtToken == null || !jwtToken.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Brak lub niepoprawny nagłówek Authorization");
        }

        String parsedToken = jwtToken.substring(7);
        System.out.println("Authenticated with token" + parsedToken);
        try {
            Long userId = jwtService.extractUserId(parsedToken);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));

            if (!jwtService.validateToken(parsedToken, user)) {
                throw new IllegalArgumentException("Token JWT jest nieprawidłowy lub wygasł");
            }

            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Błąd podczas weryfikacji tokena JWT", e);
        }
    }
}
