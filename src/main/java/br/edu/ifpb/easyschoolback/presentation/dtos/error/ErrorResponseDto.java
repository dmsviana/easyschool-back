package br.edu.ifpb.easyschoolback.presentation.dtos.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    private int code;
    private HttpStatus status;
    private String message;
    private Map<String, Object> metadata;
    private String path;
    private LocalDateTime timestamp;
}
