package br.edu.ifpb.easyschoolback.presentation.dtos.grade;

import br.edu.ifpb.easyschoolback.model.entities.GradeType;
import br.edu.ifpb.easyschoolback.model.entities.Student;

import java.time.LocalDateTime;

public record GradeResponseDto(
        Long id,
        String abbreviation,
        String description,
        Integer weight,
        Double value,
        GradeType type,
        Long studentId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
