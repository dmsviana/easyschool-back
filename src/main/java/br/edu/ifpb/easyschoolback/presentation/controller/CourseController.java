package br.edu.ifpb.easyschoolback.presentation.controller;

import br.edu.ifpb.easyschoolback.business.service.CourseService;
import br.edu.ifpb.easyschoolback.presentation.controller.contract.CourseApiContract;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.CourseResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.CreateCourseRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.UpdateCourseRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;


@RequestScope
@RestController
@RequiredArgsConstructor
public class CourseController implements CourseApiContract  {

    private final CourseService courseService;

    @Override
    public CourseResponseDto create(CreateCourseRequestDto requestDto) {
        return courseService.create(requestDto);

    }

    @Override
    public List<CourseResponseDto> getCourses() {
        return courseService.findAll();
    }

    @Override
    public CourseResponseDto getCourseById(Long courseId) {
        return courseService.findById(courseId);
    }

    @Override
    public CourseResponseDto update(Long courseId, UpdateCourseRequestDto requestDto) {
        return courseService.update(courseId, requestDto);
    }

    @Override
    public void delete(Long courseId) {
        courseService.delete(courseId);
    }

    @Override
    public void enrollStudentInCourse(Long courseId, Long studentId) {
        courseService.enrollStudentInCourse(courseId, studentId);
    }

    @Override
    public void removeStudentFromCourse(Long courseId, Long studentId) {
        courseService.removeStudentFromCourse(courseId, studentId);
    }
}
