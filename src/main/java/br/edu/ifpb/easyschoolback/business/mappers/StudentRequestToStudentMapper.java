package br.edu.ifpb.easyschoolback.business.mappers;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.CreateStudentRequestDto;

import java.util.function.Function;

public class StudentRequestToStudentMapper implements Function<CreateStudentRequestDto, Student> {


    public static Student mapper(final CreateStudentRequestDto createStudentRequest) {
        return new StudentRequestToStudentMapper().apply(createStudentRequest);
    }


    @Override
    public Student apply(final CreateStudentRequestDto createStudentRequest) {
        Student student = new Student();

        student.setFirstName(createStudentRequest.firstName());
        student.setLastName(createStudentRequest.lastName());
        student.setCpf(createStudentRequest.cpf());
        student.setEmail(createStudentRequest.email());
        student.setPassword(createStudentRequest.password());
        student.setPhone(createStudentRequest.phone());
        student.setRegistration(createStudentRequest.registration());
        student.setDateOfBirth(createStudentRequest.dateOfBirth());
        student.setParentName(createStudentRequest.parentName());
        student.setParentPhone(createStudentRequest.parentPhone());
        return student;
    }
}
