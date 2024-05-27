package br.edu.ifpb.easyschoolback.business.service;

import br.edu.ifpb.easyschoolback.model.entities.Course;
import br.edu.ifpb.easyschoolback.model.entities.Teacher;
import br.edu.ifpb.easyschoolback.model.repository.CourseRepository;
import br.edu.ifpb.easyschoolback.model.repository.TeacherRepository;
import br.edu.ifpb.easyschoolback.model.repository.exception.EntityNotFoundException;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.CreateTeacherRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.TeacherResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.UpdateTeacherRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final CpfValidationService cpfValidationService;
    private final ModelMapper mapper;

    public TeacherResponseDto create(final CreateTeacherRequestDto teacherRequest) {
        log.info("Creating teacher: {}", teacherRequest);

        cpfValidationService.validateCpf(teacherRequest.getCpf());

        Teacher createdTeacher = mapper.map(teacherRequest, Teacher.class);
        createdTeacher.setPassword(new BCryptPasswordEncoder().encode(createdTeacher.getPassword()));
        createdTeacher = this.teacherRepository.save(createdTeacher);

        log.info("Teacher created: {}", createdTeacher);
        return mapper.map(createdTeacher, TeacherResponseDto.class);
    }

    public TeacherResponseDto update(final Long id, final UpdateTeacherRequestDto updateRequest) {
        log.info("Updating teacher: {}", updateRequest);

        Teacher updatedTeacher = findTeacherEntityById(id);

        updatedTeacher.setPhone(updateRequest.getPhone());
        updatedTeacher.setEmail(updateRequest.getEmail());
        updatedTeacher.setSalary(updateRequest.getSalary());

        teacherRepository.save(updatedTeacher);
        log.info("Teacher updated: {}", updatedTeacher);

        return mapper.map(updatedTeacher, TeacherResponseDto.class);
    }


    public void delete(final Long id) {
        log.info("Deleting teacher by id: {}", id);

        Teacher teacher = findTeacherEntityById(id);

        teacherRepository.delete(teacher);
        log.info("Teacher deleted: {}", teacher);
    }

    public List<TeacherResponseDto> findAll() {
        log.info("Finding all teachers");

        List<Teacher> teachers = teacherRepository.findAll();

        log.info("Teachers found: {}", teachers);
        return teachers.stream()
                .map(teacher -> mapper.map(teacher, TeacherResponseDto.class))
                .collect(Collectors.toList());
    }

    public TeacherResponseDto findById(final Long id) {
        log.info("Finding teacher by id: {}", id);

        Teacher teacher = findTeacherEntityById(id);

        log.info("Teacher found: {}", teacher);
        return mapper.map(teacher, TeacherResponseDto.class);
    }

    public TeacherResponseDto findByCpf(final String cpf) {
        log.info("Finding teacher by cpf: {}", cpf);

        Teacher teacher = findTeacherByCpf(cpf)
                .orElseThrow(() -> {
                    log.info("Teacher not found by cpf: {}", cpf);
                    return new EntityNotFoundException();
                });

        log.info("Teacher found: {}", teacher);
        return mapper.map(teacher, TeacherResponseDto.class);
    }

    public TeacherResponseDto findByRegistration(final String registration) {
        log.info("Finding teacher by registration: {}", registration);

        Teacher teacher = teacherRepository.findByRegistration(registration)
                .orElseThrow(() -> {
                    log.info("Teacher not found by registration: {}", registration);
                    return new EntityNotFoundException();
                });

        log.info("Teacher found: {}", teacher);
        return mapper.map(teacher, TeacherResponseDto.class);
    }

    public Integer countTeachersOnCurrentMonth() {
        log.info("Counting teachers on current month");

        Integer count = teacherRepository.countTeachersOnCurrentMonth();

        log.info("Teachers counted: {}", count);
        return count;
    }

    public Integer countTotalTeachers() {
        log.info("Counting total teachers");

        Integer count = teacherRepository.countTotalTeachers();

        log.info("Teachers counted: {}", count);
        return count;
    }

    public void assignCourseToTeacher(Long teacherId, Long courseId) {
        log.info("Enrolling teacher {} in course {}", teacherId, courseId);

        Course course = findCourseEntityById(courseId);
        Teacher teacher = findTeacherEntityById(teacherId);

        if (course.getTeachers().contains(teacher)) {
            log.info("Teacher {} is already enrolled in course {}", teacherId, courseId);
            return;
        }

        course.addTeacher(teacher);
        courseRepository.save(course);

        log.info("Teacher {} enrolled in course {}", teacherId, courseId);
    }

    public void removeTeacherFromCourse(Long teacherId, Long courseId) {
        log.info("Removing teacher {} from course {}", teacherId, courseId);

        Course course = findCourseEntityById(courseId);
        Teacher teacher = findTeacherEntityById(teacherId);

        if (!course.getTeachers().contains(teacher)) {
            log.info("Teacher {} is not enrolled in course {}", teacherId, courseId);
            return;
        }

        course.removeTeacher(teacher);
        courseRepository.save(course);

        log.info("Teacher {} removed from course {}", teacherId, courseId);
    }

    private Optional<Teacher> findTeacherByCpf(final String cpf) {
        return teacherRepository.findByCpf(cpf);
    }

    private Teacher findTeacherEntityById(final Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Teacher not found by id: {}", id);
                    return new EntityNotFoundException();
                });
    }

    private Course findCourseEntityById(final Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Course not found by id: {}", id);
                    return new EntityNotFoundException();
                });
    }
}