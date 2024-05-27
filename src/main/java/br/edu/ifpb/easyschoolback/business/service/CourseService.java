package br.edu.ifpb.easyschoolback.business.service;

import br.edu.ifpb.easyschoolback.model.entities.Course;
import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.repository.CourseRepository;
import br.edu.ifpb.easyschoolback.model.repository.StudentRepository;
import br.edu.ifpb.easyschoolback.model.repository.exception.EntityNotFoundException;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.CourseResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.CreateCourseRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.UpdateCourseRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    public CourseResponseDto create(final CreateCourseRequestDto courseRequest) {
        log.info("Creating course: {}", courseRequest);

        Course createdCourse = mapper.map(courseRequest, Course.class);
        createdCourse = this.courseRepository.save(createdCourse);

        log.info("Course created: {}", createdCourse);
        return mapper.map(createdCourse, CourseResponseDto.class);
    }

    public CourseResponseDto findById(final Long id) {
        log.info("Finding course by id: {}", id);

        Course course = findCourseEntityById(id);

        log.info("Course found: {}", course);
        return mapper.map(course, CourseResponseDto.class);
    }

    public List<CourseResponseDto> findAll() {
        log.info("Finding all courses");

        List<Course> courses = courseRepository.findAll();

        log.info("Courses found: {}", courses);
        return courses.stream()
                .map(course -> mapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }

    public CourseResponseDto update(final Long id, final UpdateCourseRequestDto updateRequest) {
        log.info("Updating course: {}", updateRequest);

        Course course = findCourseEntityById(id);

        course.setMaxCapacity(updateRequest.getMaxCapacity());
        course.setMinAge(updateRequest.getMinAge());
        course.setMaxAge(updateRequest.getMaxAge());
        course.setDaysOfWeek(updateRequest.getDaysOfWeek());

        course = courseRepository.save(course);

        log.info("Course updated: {}", course);
        return mapper.map(course, CourseResponseDto.class);
    }

    public void delete(final Long id) {
        log.info("Deleting course by id: {}", id);

        Course course = findCourseEntityById(id);

        courseRepository.delete(course);

        log.info("Course deleted: {}", course);
    }

    public void enrollStudentInCourse(Long courseId, Long studentId) {
        log.info("Enrolling student {} in course {}", studentId, courseId);

        Course course = findCourseEntityById(courseId);
        Student student = findStudentEntityById(studentId);

        if (course.getStudents().contains(student)) {
            log.info("Student {} is already enrolled in course {}", studentId, courseId);
            return;
        }

        course.addStudent(student);
        courseRepository.save(course);

        log.info("Student {} enrolled in course {}", studentId, courseId);
    }

    public void removeStudentFromCourse(Long courseId, Long studentId) {
        log.info("Removing student {} from course {}", studentId, courseId);

        Course course = findCourseEntityById(courseId);
        Student student = findStudentEntityById(studentId);

        if (!course.getStudents().contains(student)) {
            log.info("Student {} is not enrolled in course {}", studentId, courseId);
            return;
        }

        course.removeStudent(student.getId());
        courseRepository.save(course);

        log.info("Student {} removed from course {}", studentId, courseId);
    }

    public Integer countTotalCourses() {
        log.info("Counting total courses");

        Integer totalCourses = courseRepository.countTotalCourses();

        log.info("Total courses: {}", totalCourses);
        return totalCourses;
    }

    public Integer countStudentsByCourseId(final Long courseId) {
        log.info("Counting students by course id: {}", courseId);

        Integer totalStudents = courseRepository.countStudentsByCourseId(courseId);

        log.info("Total students by course id: {}", totalStudents);
        return totalStudents;
    }

    private Course findCourseEntityById(final Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Course not found by id: {}", id);
                    return new EntityNotFoundException();
                });
    }

    private Optional<Course> findCourseEntityByName(final String name) {
        log.info("Finding course by name: {}", name);
        return courseRepository.findByName(name);
    }


    private Student findStudentEntityById(final Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Student not found by id: {}", id);
                    return new EntityNotFoundException();
                });
    }
}
