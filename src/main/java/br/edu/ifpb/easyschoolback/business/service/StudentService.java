package br.edu.ifpb.easyschoolback.business.service;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.repository.CourseRepository;
import br.edu.ifpb.easyschoolback.model.repository.StudentRepository;
import br.edu.ifpb.easyschoolback.model.repository.exception.EntityNotFoundException;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.CreateStudentRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.StudentResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.UpdateStudentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CpfValidationService cpfValidationService;
    private final ModelMapper mapper;


    public StudentResponseDto create(final CreateStudentRequestDto studentRequest) {
        log.info("Creating student: {}", studentRequest);

        cpfValidationService.validateCpf(studentRequest.getCpf());

        Student createdStudent = mapper.map(studentRequest, Student.class);
        createdStudent.setPassword(new BCryptPasswordEncoder().encode(createdStudent.getPassword()));
        createdStudent = this.studentRepository.save(createdStudent);

        log.info("Student created: {}", createdStudent);
        return mapper.map(createdStudent, StudentResponseDto.class);
    }

    public StudentResponseDto update(final Long id, final UpdateStudentRequestDto updateRequest) {
        log.info("Updating student: {}", updateRequest);

        Student updatedStudent = findStudentById(id);

        if (updatedStudent.getAge() < 18) {
            updatedStudent.setParentName(updateRequest.getParentName());
            updatedStudent.setParentPhone(updateRequest.getParentPhone());
        }
        updatedStudent.setPhone(updateRequest.getPhone());
        updatedStudent.setEmail(updateRequest.getEmail());

        studentRepository.save(updatedStudent);
        log.info("Student updated: {}", updatedStudent);

        return mapper.map(updatedStudent, StudentResponseDto.class);
    }

    @Transactional
    public void delete(final Long id) {
        log.info("Deleting student by id: {}", id);

        Student student = findStudentById(id);

        courseRepository.removeStudentFromAllCourses(id);

        studentRepository.delete(student);
        log.info("Student with id deleted: {}", id);
    }

    public List<StudentResponseDto> findAll() {
        log.info("Finding all students");

        List<Student> students = studentRepository.findAll();

        log.info("Students found: {}", students);
        return students.stream()
                .map(student -> mapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());
    }

    public StudentResponseDto findById(final Long id) {
        log.info("Finding student by id: {}", id);

        Student student = findStudentById(id);

        log.info("Student found: {}", student);
        return mapper.map(student, StudentResponseDto.class);
    }

    public StudentResponseDto findByCpf(final String cpf) {
        log.info("Finding student by cpf: {}", cpf);
        Optional<Student> student = findStudentByCpf(cpf);

        if (student.isEmpty()) {
            throw new EntityNotFoundException();
        }

        log.info("Student found: {}", student.get());
        return mapper.map(student.get(), StudentResponseDto.class);
    }

    public StudentResponseDto findByRegistration(final String registration) {
        log.info("Finding student by registration: {}", registration);

        Student student = studentRepository.findByRegistration(registration)
                .orElseThrow(() -> {
                    log.info("Student not found by registration: {}", registration);
                    return new EntityNotFoundException();
                });

        log.info("Student found: {}", student);
        return mapper.map(student, StudentResponseDto.class);
    }

    public Integer countTotalStudents() {
        log.info("Counting total students");

        Integer count = studentRepository.countTotalStudents();

        log.info("Students counted: {}", count);
        return count;
    }

    public Integer countStudentsOnCurrentMonth() {
        log.info("Counting students on current month");

        Integer count = studentRepository.countStudentsOnCurrentMonth();

        log.info("Students counted: {}", count);
        return count;
    }

    private Optional<Student> findStudentByCpf(final String cpf) {
        return studentRepository.findByCpf(cpf);
    }

    private Student findStudentById(final Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Student not found by id: {}", id);
                    return new EntityNotFoundException();
                });
    }
}
