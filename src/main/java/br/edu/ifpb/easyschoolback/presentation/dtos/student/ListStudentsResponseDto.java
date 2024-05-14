package br.edu.ifpb.easyschoolback.presentation.dtos.student;

import java.util.List;

public record ListStudentsResponseDto(
        List<StudentResponseDto> students
) {
}
