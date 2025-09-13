package com.voxever.super_chess.auth.dto.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {
    private String status;
    private String email;
    private String username;
}