package br.edu.ifpb.easyschoolback.business.service;


import br.edu.ifpb.easyschoolback.business.mappers.GradeRequestToGradeMapper;
import br.edu.ifpb.easyschoolback.business.mappers.GradeToGradeResponseMapper;
import br.edu.ifpb.easyschoolback.business.mappers.StudentRequestToStudentMapper;
import br.edu.ifpb.easyschoolback.model.entities.Grade;
import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.repository.GradeRepository;
import br.edu.ifpb.easyschoolback.model.repository.StudentRepository;
import br.edu.ifpb.easyschoolback.model.repository.exception.EntityNotFoundException;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.CreateGradeRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.GradeResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.StudentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;


    public GradeResponseDto assignGradeToStudent(final CreateGradeRequestDto gradeRequest, final Long studentId) {
        log.info("Assigning grade to student: {}", studentId);

        Grade grade = GradeRequestToGradeMapper.mapper(gradeRequest);

        Student student = studentRepository.findById(studentId).orElseThrow(
                EntityNotFoundException::new);

        grade.setStudent(student);

        gradeRepository.save(grade);
        return GradeToGradeResponseMapper.mapper(grade);
    }

    public void removeGradeFromStudent(final Long gradeId) {
        log.info("Removing grade from student: {}", gradeId);

        Grade grade = findGradeEntityById(gradeId);

        gradeRepository.delete(grade);
    }



    private Grade findGradeEntityById(final Long gradeId) {
        return gradeRepository.findById(gradeId).orElseThrow(
                EntityNotFoundException::new);
    }
}
