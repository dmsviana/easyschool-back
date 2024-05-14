package br.edu.ifpb.easyschoolback.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public abstract class SchoolException extends RuntimeException {
    protected final HttpStatus status;
    protected final Map<String, Object> metadata;

    public SchoolException(
            final String message,
            final HttpStatus status,
            final Map<String, Object> metadata) {
        super(message);
        this.status = status;
        this.metadata = metadata;
    }

}
