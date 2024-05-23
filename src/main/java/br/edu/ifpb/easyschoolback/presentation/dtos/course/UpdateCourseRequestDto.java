package br.edu.ifpb.easyschoolback.presentation.dtos.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseRequestDto {
    private Integer maxCapacity;
    private Integer minAge;
    private Integer maxAge;
    private List<DayOfWeek> daysOfWeek;
}