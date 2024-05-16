package br.edu.ifpb.easyschoolback.business.mappers;

import br.edu.ifpb.easyschoolback.model.entities.Course;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.CourseResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.ListStudentsResponseDto;

import java.util.function.Function;

public class CourseToCourseResponseMapper implements Function<Course, CourseResponseDto> {


    public static CourseResponseDto mapper(final Course course) {
        return new CourseToCourseResponseMapper().apply(course);
    }

    @Override
    public CourseResponseDto apply(final Course course) {

        StudentToStudentResponseMapper studentToStudentResponseMapper = new StudentToStudentResponseMapper();
        ListStudentsResponseDto students = new ListStudentsResponseDto(course.getStudents()
                .stream()
                .map(studentToStudentResponseMapper)
                .toList());


        return new CourseResponseDto(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getMaxCapacity(),
                course.getMinAge(),
                course.getMaxAge(),
                course.getCourseDays(),
                students,
                course.getCreatedAt(),
                course.getUpdatedAt());
    }
}
