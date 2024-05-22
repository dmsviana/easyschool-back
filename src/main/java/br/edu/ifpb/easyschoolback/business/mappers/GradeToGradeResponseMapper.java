package br.edu.ifpb.easyschoolback.business.mappers;

import br.edu.ifpb.easyschoolback.model.entities.Grade;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.GradeResponseDto;
import java.util.function.Function;

public class GradeToGradeResponseMapper implements Function<Grade, GradeResponseDto> {

    public static GradeResponseDto mapper(final Grade grade) {
        return new GradeToGradeResponseMapper().apply(grade);
    }

    @Override
    public GradeResponseDto apply(final Grade grade) {
        return new GradeResponseDto(
                grade.getId(),
                grade.getAbbreviation(),
                grade.getDescription(),
                grade.getWeight(),
                grade.getValue(),
                grade.getType(),
                grade.getStudent().getId(),
                grade.getCreatedAt(),
                grade.getUpdatedAt());
    }

}
