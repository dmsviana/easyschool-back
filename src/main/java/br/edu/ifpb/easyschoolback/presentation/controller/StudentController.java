package br.edu.ifpb.easyschoolback.presentation.controller;


import br.edu.ifpb.easyschoolback.business.service.StudentService;
import br.edu.ifpb.easyschoolback.presentation.controller.contract.StudentApiContract;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.CreateStudentRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.StudentResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.UpdateStudentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RequestScope
@RestController
@RequiredArgsConstructor
public class StudentController implements StudentApiContract {

    private final StudentService studentService;

    @Override
    public StudentResponseDto create(CreateStudentRequestDto requestDto) {
        return studentService.create(requestDto);
    }

    @Override
    public List<StudentResponseDto> getStudents() {
        return studentService.findAll();
    }

    @Override
    public StudentResponseDto getStudentById(Long studentId) {
        return studentService.findById(studentId);
    }

    @Override
    public StudentResponseDto update(Long studentId, UpdateStudentRequestDto requestDto) {
        return studentService.update(studentId, requestDto);
    }

    @Override
    public void delete(Long studentId) {
        studentService.delete(studentId);
    }
}
