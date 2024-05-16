package br.edu.ifpb.easyschoolback.presentation.advice;

import br.edu.ifpb.easyschoolback.model.exception.SchoolBusinessException;
import br.edu.ifpb.easyschoolback.model.repository.exception.MaximumCapacityReachedException;
import br.edu.ifpb.easyschoolback.presentation.dtos.error.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SchoolBusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFound(
            SchoolBusinessException ex,
            ServletWebRequest servletWebRequest) {
        var error = createError(
                ex.getStatus(),
                ex.getMessage(),
                ex.getMetadata(),
                servletWebRequest
        );
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(
            IllegalArgumentException e,
            ServletWebRequest servletWebRequest) {
        var error = createError(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                Map.of(),
                servletWebRequest
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            ServletWebRequest servletWebRequest) {
        Map<String, Object> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldErr -> {
            errors.put(
                    "field[" + fieldErr.getField() + "]",
                    fieldErr.getDefaultMessage()
            );
        });

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("errors", errors);

        var error = createError(
                HttpStatus.BAD_REQUEST,
                "Ops! Não conseguimos processar a sua requisição",
                metadata,
                servletWebRequest
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception e,
            ServletWebRequest servletWebRequest) {
        var error = createError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ops! Algo não deu certo",
                Map.of(
                        "description", e.getLocalizedMessage()
                ),
                servletWebRequest
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MaximumCapacityReachedException.class)
    public ResponseEntity<ErrorResponseDto> handleMaximumCapacityReachedException(
            MaximumCapacityReachedException e,
            ServletWebRequest servletWebRequest) {
        var error = createError(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                Map.of(),
                servletWebRequest
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    private ErrorResponseDto createError(
            HttpStatus status,
            String message,
            Map<String, Object> metadata,
            ServletWebRequest request
    ) {
        return new ErrorResponseDto(
                status.value(),
                status,
                message,
                metadata,
                request.getRequest().getRequestURL().toString(),
                LocalDateTime.now()
        );
    }

}
