package com.voxever.super_chess.exception;

import com.voxever.super_chess.dto.error.ErrorResponseDto;
import com.voxever.super_chess.exception.custom.auth.CredentialsAlreadyTakenException;
import com.voxever.super_chess.exception.custom.auth.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CredentialsAlreadyTakenException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExists(CredentialsAlreadyTakenException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseDto.builder()
                .timestamp(Instant.now().toEpochMilli())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return ResponseEntity.unprocessableEntity().body(ErrorResponseDto.builder()
                .timestamp(Instant.now().toEpochMilli())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .message(errors)
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponseDto.builder()
                .timestamp(Instant.now().toEpochMilli())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.builder()
                .timestamp(Instant.now().toEpochMilli())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        return ResponseEntity.status(status).body(ErrorResponseDto.builder()
                .timestamp(Instant.now().toEpochMilli())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getReason())
                .path(request.getRequestURI())
                .build());
    }
}