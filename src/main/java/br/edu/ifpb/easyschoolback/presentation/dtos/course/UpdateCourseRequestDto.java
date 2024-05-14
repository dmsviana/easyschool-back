package br.edu.ifpb.easyschoolback.presentation.dtos.course;

import java.time.DayOfWeek;
import java.util.List;

public record UpdateCourseRequestDto(
        Integer maxCapacity,
        Integer minAge,
        Integer maxAge,
        List<DayOfWeek> daysOfWeek
) {
}
