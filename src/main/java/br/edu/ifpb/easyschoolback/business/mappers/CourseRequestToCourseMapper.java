package br.edu.ifpb.easyschoolback.business.mappers;

import br.edu.ifpb.easyschoolback.model.entities.Course;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.CreateCourseRequestDto;

import java.util.function.Function;

public class CourseRequestToCourseMapper implements Function<CreateCourseRequestDto, Course> {


    public static Course mapper(final CreateCourseRequestDto createCourseRequestDto) {
        return new CourseRequestToCourseMapper().apply(createCourseRequestDto);
    }

    @Override
    public Course apply(final CreateCourseRequestDto createCourseRequestDto) {
        Course course = new Course();

        course.setName(createCourseRequestDto.name());
        course.setDescription(createCourseRequestDto.description());
        course.setMaxCapacity(createCourseRequestDto.maxCapacity());
        course.setMinAge(createCourseRequestDto.minAge());
        course.setMaxAge(createCourseRequestDto.maxAge());
        course.setCourseDays(createCourseRequestDto.daysOfWeek());

        return course;
    }
}
