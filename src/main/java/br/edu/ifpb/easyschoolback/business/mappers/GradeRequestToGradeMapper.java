package br.edu.ifpb.easyschoolback.business.mappers;

import br.edu.ifpb.easyschoolback.model.entities.Grade;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.CreateGradeRequestDto;

import java.util.function.Function;

public class GradeRequestToGradeMapper implements Function<CreateGradeRequestDto, Grade> {

    public static Grade mapper(final CreateGradeRequestDto createRequest) {
        return new GradeRequestToGradeMapper().apply(createRequest);
    }
    @Override
    public Grade apply(final CreateGradeRequestDto createRequest) {

        Grade grade = new Grade();

        grade.setAbbreviation(createRequest.abbreviation());
        grade.setDescription(createRequest.description());
        grade.setWeight(createRequest.weight());
        grade.setValue(createRequest.value());
        grade.setType(createRequest.type());

        return grade;
    }
}
