package br.edu.ifpb.easyschoolback.model.repository.exception;

import br.edu.ifpb.easyschoolback.model.exception.SchoolBusinessException;

import java.util.Map;

public class EntityNotFoundException extends SchoolBusinessException {
    public EntityNotFoundException() {
        super(
                "Ops! Não foi encontrado nenhum registro correspondente com essa busca",
                Map.of()
        );
    }

    public EntityNotFoundException(String message) {
        super(
                message,
                Map.of()
        );
    }
}