package com.voxever.super_chess.service;

import com.voxever.super_chess.repository.UserRepository;
import com.voxever.super_chess.service.auth.AuthContextService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final AuthContextService authContext;

    public UserService(UserRepository userRepo, AuthContextService authContext, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.authContext = authContext;
        this.encoder = encoder;
    }

//    public ResponseEntity<UserResponseDto> getUserDataByJwt(String jwtToken) {
//        User foundUser = authContext.getUserFromToken(jwtToken);
//
//        return ResponseEntity.ok().body(UserResponseDto.builder()
//                .email(foundUser.getEmail())
//                .id(foundUser.getUserId())
//                .username(foundUser.getDisplayName())
//                .createdAt(foundUser.getCreatedAt().toEpochMilli())
//                .updatedAt(foundUser.getUpdatedAt().toEpochMilli())
//                .build()
//        );
//    }
//
//    public ResponseEntity<UserUpdateResponseDto> updateUserByJwt(String jwtToken, UserUpdateRequestDto request) {
//        User foundUser = authContext.getUserFromToken(jwtToken);
//
//        User updatedUser = userRepo.save(updateUserEntity(foundUser, request));
//
//        return ResponseEntity.ok(UserUpdateResponseDto.builder()
//                .status("Success!")
//                .displayName(updatedUser.getDisplayName())
//                .email(updatedUser.getEmail())
//                .updatedAt(updatedUser.getUpdatedAt().toEpochMilli())
//                .build());
//    }
//
//    private User updateUserEntity(User foundUser, UserUpdateRequestDto request){
//        if(!StringUtils.isBlank(request.getDisplayName()))
//            foundUser.setDisplayName(request.getDisplayName());
//
//        if(!StringUtils.isBlank(request.getEmail()))
//            foundUser.setEmail(request.getEmail());
//
//        if(!StringUtils.isBlank(request.getMasterHash()))
//            foundUser.setMasterHash(encoder.encode(request.getMasterHash()));
//
//        return foundUser;
//    }
//
//    @Transactional
//    public ResponseEntity<StatusResponseDto> deleteUserByJwt(String jwtToken, DeleteUserRequestDto request) {
//        User foundUser = authContext.getUserFromToken(jwtToken);
//
//        if(encoder.matches(request.getMasterHash(), foundUser.getMasterHash())) userRepo.delete(foundUser);
//        else throw new PasswordMatchException("Passwords do not match!");
//
//        return ResponseEntity.ok().body(StatusResponseDto.builder()
//                .status("Success!")
//                .build());
//    }
}