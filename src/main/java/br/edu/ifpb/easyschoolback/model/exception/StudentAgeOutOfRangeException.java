package br.edu.ifpb.easyschoolback.model.exception;

import java.util.Map;

public class StudentAgeOutOfRangeException extends SchoolBusinessException {

    public StudentAgeOutOfRangeException(final int minAge, final int maxAge) {
        super(
                "Ops! Verificamos que a idade do aluno não está dentro do intervalo permitido pelo curso. " +
                        "A idade do aluno deve estar entre " + minAge + " e " + maxAge + " anos.",
                Map.of()
        );
    }

    public StudentAgeOutOfRangeException(String message) {
        super(
                message,
                Map.of()
        );
    }
}