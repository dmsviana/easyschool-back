package br.edu.ifpb.easyschoolback.presentation.dtos.teacher;

import java.util.List;

public record ListTeachersResponseDto(
        List<TeacherResponseDto> teachers
) {
}
