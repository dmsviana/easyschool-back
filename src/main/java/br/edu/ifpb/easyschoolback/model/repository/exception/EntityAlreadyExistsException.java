package br.edu.ifpb.easyschoolback.model.repository.exception;

import br.edu.ifpb.easyschoolback.model.exception.SchoolBusinessException;

import java.util.Map;


public class EntityAlreadyExistsException extends SchoolBusinessException {

    public EntityAlreadyExistsException() {
        super(
                "Ops! Verificamos que esse cadastro já existe",
                Map.of()
        );
    }

    public EntityAlreadyExistsException(String message) {
        super(
                message,
                Map.of()
        );
    }
}
