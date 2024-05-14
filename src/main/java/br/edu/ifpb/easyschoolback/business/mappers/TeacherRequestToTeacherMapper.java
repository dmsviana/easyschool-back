package br.edu.ifpb.easyschoolback.business.mappers;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.entities.Teacher;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.CreateStudentRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.CreateTeacherRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.TeacherResponseDto;

import java.util.function.Function;

public class TeacherRequestToTeacherMapper implements Function<CreateTeacherRequestDto, Teacher> {

    public static Teacher mapper(final CreateTeacherRequestDto createTeacherRequest) {
        return new TeacherRequestToTeacherMapper().apply(createTeacherRequest);
    }


    @Override
    public Teacher apply(final CreateTeacherRequestDto createTeacherRequest) {
        Teacher teacher = new Teacher();

        teacher.setFirstName(createTeacherRequest.firstName());
        teacher.setLastName(createTeacherRequest.lastName());
        teacher.setCpf(createTeacherRequest.cpf());
        teacher.setEmail(createTeacherRequest.email());
        teacher.setPassword(createTeacherRequest.password());
        teacher.setSalary(createTeacherRequest.salary());
        teacher.setPhone(createTeacherRequest.phone());
        teacher.setRegistration(createTeacherRequest.registration());
        teacher.setDateOfBirth(createTeacherRequest.dateOfBirth());

        return teacher;
    }
}
