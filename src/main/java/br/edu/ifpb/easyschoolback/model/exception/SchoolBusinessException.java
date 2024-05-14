package br.edu.ifpb.easyschoolback.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public abstract class SchoolBusinessException extends SchoolException {
    protected SchoolBusinessException(
            String message,
            HttpStatus status,
            Map<String, Object> metadata
    ) {
        super(message, status, metadata);
    }

    protected SchoolBusinessException(
            String message,
            Map<String, Object> metadata
    ) {
        super(message, HttpStatus.BAD_REQUEST, metadata);
    }

    protected SchoolBusinessException(
            String message
    ) {
        super(
                message,
                HttpStatus.BAD_REQUEST,
                Map.of("description", "Ops! Não conseguimos processar a sua requisição")
        );
    }
}