package br.edu.ifpb.easyschoolback.presentation.controller;


import br.edu.ifpb.easyschoolback.business.service.TeacherService;
import br.edu.ifpb.easyschoolback.presentation.controller.contract.TeacherApiContract;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.CreateTeacherRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.TeacherResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.UpdateTeacherRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RequestScope
@RestController
@RequiredArgsConstructor
public class TeacherController implements TeacherApiContract {

    private final TeacherService teacherService;


    @Override
    public TeacherResponseDto create(CreateTeacherRequestDto requestDto) {
        return teacherService.create(requestDto);
    }

    @Override
    public List<TeacherResponseDto> getTeachers() {
        return teacherService.findAll();
    }

    @Override
    public TeacherResponseDto getTeacherById(Long teacherId) {
        return teacherService.findById(teacherId);
    }

    @Override
    public TeacherResponseDto update(Long teacherId, UpdateTeacherRequestDto requestDto) {
        return teacherService.update(teacherId, requestDto);
    }

    @Override
    public void delete(Long teacherId) {
        teacherService.delete(teacherId);
    }

    @Override
    public void assignCourseToTeacher(Long teacherId, Long courseId) {
        teacherService.assignCourseToTeacher(teacherId, courseId);
    }

    @Override
    public void removeCourseFromTeacher(Long teacherId, Long courseId) {
        teacherService.removeTeacherFromCourse(teacherId, courseId);
    }


}
