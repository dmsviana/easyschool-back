package br.edu.ifpb.easyschoolback.business.mappers;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.entities.Teacher;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.StudentResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.TeacherResponseDto;

import java.util.function.Function;

public class TeacherToTeacherResponseMapper implements Function<Teacher, TeacherResponseDto> {

    public static TeacherResponseDto mapper(final Teacher teacher) {
        return new TeacherToTeacherResponseMapper().apply(teacher);
    }

    @Override
    public TeacherResponseDto apply(final Teacher teacher) {
        return new TeacherResponseDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getCpf(),
                teacher.getEmail(),
                teacher.getPhone(),
                teacher.getRegistration(),
                teacher.getSalary(),
                teacher.getDateOfBirth(),
                teacher.getRegistrationDate(),
                teacher.getActive(),
                teacher.getCreatedAt(),
                teacher.getUpdatedAt());
    }



}
