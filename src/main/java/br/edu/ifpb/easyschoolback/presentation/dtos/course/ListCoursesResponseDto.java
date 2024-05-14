package br.edu.ifpb.easyschoolback.presentation.dtos.course;

import java.util.List;

public record ListCoursesResponseDto(
        List<CourseResponseDto> courses
) {
}
