package br.edu.ifpb.easyschoolback.presentation.dtos.course;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.ListStudentsResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.StudentResponseDto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public record CourseResponseDto(
        Long id,
        String name,
        String description,
        Integer maxCapacity,
        Integer minAge,
        Integer maxAge,
        List<DayOfWeek> daysOfWeek,
        ListStudentsResponseDto students,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
