package br.edu.ifpb.easyschoolback.presentation.dtos.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDto(
        int code,
        HttpStatus status,
        String message,
        Map<String, Object> metadata,
        String path,
        LocalDateTime timestamp
) {
}
