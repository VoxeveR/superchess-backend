package com.voxever.super_chess.auth.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {

    @JsonProperty("access_token_type")
    private String accessTokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("access_token_expires_in")
    private long accessTokenExpiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("refresh_token_expires_in")
    private long refreshTokenExpiresIn;

    @JsonProperty("username")
    private String username;

}