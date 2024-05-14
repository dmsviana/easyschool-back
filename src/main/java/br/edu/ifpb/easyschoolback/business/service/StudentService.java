package br.edu.ifpb.easyschoolback.business.service;

import br.edu.ifpb.easyschoolback.business.mappers.StudentRequestToStudentMapper;
import br.edu.ifpb.easyschoolback.business.mappers.StudentToStudentResponseMapper;
import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.repository.CourseRepository;
import br.edu.ifpb.easyschoolback.model.repository.StudentRepository;
import br.edu.ifpb.easyschoolback.model.repository.exception.EntityNotFoundException;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.CreateStudentRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.StudentResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.UpdateStudentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public StudentResponseDto create(final CreateStudentRequestDto studentRequest) {
        log.info("Creating student: {}", studentRequest);

        Optional<Student> studentExists = findStudentByCpf(studentRequest.cpf());

        if (studentExists.isPresent()) {
            log.info("Student already exists: {}", studentExists.get());
            return StudentToStudentResponseMapper.mapper(studentExists.get());
        }

        Student createdStudent = StudentRequestToStudentMapper.mapper(studentRequest);
        createdStudent = this.studentRepository.save(createdStudent);

        log.info("Student created: {}", createdStudent);
        return StudentToStudentResponseMapper.mapper(createdStudent);
    }


    public StudentResponseDto update(final Long id, final UpdateStudentRequestDto updateRequest) {
        log.info("Updating student: {}", updateRequest);

        Student updatedStudent = findStudentById(id);

        if(updatedStudent.getAge() < 18) {
            updatedStudent.setParentName(updateRequest.parentName());
            updatedStudent.setParentPhone(updateRequest.parentPhone());
        }
        updatedStudent.setPhone(updateRequest.phone());
        updatedStudent.setEmail(updateRequest.email());

        studentRepository.save(updatedStudent);
        log.info("Student updated: {}", updatedStudent);

        return StudentToStudentResponseMapper.mapper(updatedStudent);
    }

    @Transactional
    public void delete(final Long id) {
        log.info("Deleting student by id: {}", id);

        courseRepository.removeStudentFromAllCourses(id);

        studentRepository.deleteById(id);
        log.info("Student with id deleted: {}", id);
    }

    public List<StudentResponseDto> findAll() {
        log.info("Finding all students");

        List<Student> students = studentRepository.findAll();

        log.info("Students found: {}", students);
        return students.stream()
                .map(StudentToStudentResponseMapper::mapper)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findById(final Long id) {
        log.info("Finding student by id: {}", id);

        Student student = findStudentById(id);

        log.info("Student found: {}", student);
        return StudentToStudentResponseMapper.mapper(student);
    }

    public StudentResponseDto findByCpf(final String cpf) {
        log.info("Finding student by cpf: {}", cpf);

        Student student = findStudentByCpf(cpf)
                .orElseThrow(() -> {
                    log.info("Student not found by cpf: {}", cpf);
                    return new EntityNotFoundException("Student not found");
                });

        log.info("Student found: {}", student);
        return StudentToStudentResponseMapper.mapper(student);
    }

    public StudentResponseDto findByRegistration(final String registration) {
        log.info("Finding student by registration: {}", registration);

        Student student = studentRepository.findByRegistration(registration)
                .orElseThrow(() -> {
                    log.info("Student not found by registration: {}", registration);
                    return new EntityNotFoundException("Student not found");
                });

        log.info("Student found: {}", student);
        return StudentToStudentResponseMapper.mapper(student);
    }


    public Integer countStudentsOnCurrentMonth(){
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
                    return new EntityNotFoundException("Student not found");
                });
    }


}