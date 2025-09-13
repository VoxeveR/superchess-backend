package com.voxever.super_chess.auth.config.oauth2;

import com.voxever.super_chess.auth.entity.User;
import com.voxever.super_chess.auth.service.auth.AuthService;
import com.voxever.super_chess.auth.service.auth.JwtService;
import com.voxever.super_chess.auth.service.auth.RefreshTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class Oauth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final AuthService authService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Value("${frontend.url}")
    private String frontendUrl;

    public Oauth2LoginSuccessHandler(AuthService authService, JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String registrationId = oauthToken.getAuthorizedClientRegistrationId();

        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();

        String email = attributes.getOrDefault("email", "").toString();
        String username;

        if ("github".equals(registrationId)) {
            username = attributes.getOrDefault("login", "").toString();
        } else if ("google".equals(registrationId)) {
            username = email.split("@")[0];
        } else {
            username = "";
        }

        Optional<User> userOpt = authService.findByEmail(email);

        User user = userOpt.orElseGet(() -> authService.registerOAuthUser(email, username, registrationId));
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .toList();

        DefaultOAuth2User oauthUser = new DefaultOAuth2User(
                authorities,
                attributes,
                "sub" // lub odpowiedni klucz id - możesz go dynamicznie dopasować
        );

        Authentication newAuth = new OAuth2AuthenticationToken(
                oauthUser,
                authorities,
                registrationId
        );

        SecurityContextHolder.getContext().setAuthentication(newAuth);

        String jwtToken = jwtService.generateToken(user.getUserId());
        String refreshToken = refreshTokenService.createRefreshToken(user).getRawToken();

        String targetUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/redirect")
                .queryParam("jwtToken", jwtToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();

        setAlwaysUseDefaultTargetUrl(true);
        setDefaultTargetUrl(targetUrl);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
