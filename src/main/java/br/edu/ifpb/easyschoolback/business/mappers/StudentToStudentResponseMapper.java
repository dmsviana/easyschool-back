package br.edu.ifpb.easyschoolback.business.mappers;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.StudentResponseDto;

import java.util.function.Function;

public class StudentToStudentResponseMapper implements Function<Student, StudentResponseDto> {


    public static StudentResponseDto mapper(final Student student) {
        return new StudentToStudentResponseMapper().apply(student);
    }

    @Override
    public StudentResponseDto apply(final Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getParentName(),
                student.getParentPhone(),
                student.getCpf(),
                student.getEmail(),
                student.getPhone(),
                student.getRegistration(),
                student.getDateOfBirth(),
                student.getRegistrationDate(),
                student.getActive(),
                student.getCreatedAt(),
                student.getUpdatedAt());
    }
}
