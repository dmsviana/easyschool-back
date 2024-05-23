package br.edu.ifpb.easyschoolback.presentation.dtos.course;

import br.edu.ifpb.easyschoolback.presentation.dtos.student.ListStudentsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponseDto {

    private Long id;
    private String name;
    private String description;
    private Integer maxCapacity;
    private Integer minAge;
    private Integer maxAge;
    private List<DayOfWeek> daysOfWeek;
    private ListStudentsResponseDto students;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
