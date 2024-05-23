package br.edu.ifpb.easyschoolback.business.service;


import br.edu.ifpb.easyschoolback.model.entities.Grade;
import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.repository.GradeRepository;
import br.edu.ifpb.easyschoolback.model.repository.StudentRepository;
import br.edu.ifpb.easyschoolback.model.repository.exception.EntityNotFoundException;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.CreateGradeRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.GradeResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.UpdateGradeRequestDto;
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
    private final ModelMapper mapper;

    public GradeResponseDto assignGradeToStudent(final CreateGradeRequestDto gradeRequest, final Long studentId) {
        log.info("Assigning grade to student: {}", studentId);

        Grade grade = mapper.map(gradeRequest, Grade.class);

        Student student = studentRepository.findById(studentId).orElseThrow(
                EntityNotFoundException::new);

        grade.setStudent(student);

        gradeRepository.save(grade);
        return mapper.map(grade, GradeResponseDto.class);
    }

    public void removeGradeFromStudent(final Long gradeId, final Long studentId) {
        log.info("Removing grade from student: {}", gradeId);

        Grade grade = findGradeEntityById(gradeId);
        Student student = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);

        if (!grade.getStudent().equals(student)) {
            throw new EntityNotFoundException();
        }

        gradeRepository.delete(grade);

        log.info("Grade removed from student: {}", gradeId);
    }

    public GradeResponseDto updateGrade(final Long gradeId, final UpdateGradeRequestDto updateRequest) {

        log.info("Updating grade: {}", updateRequest);

        Grade grade = findGradeEntityById(gradeId);

        grade.setGradeValue(updateRequest.getGradeValue());
        grade.setWeight(updateRequest.getWeight());
        grade.setType(updateRequest.getType());

        gradeRepository.save(grade);

        log.info("Grade updated: {}", gradeId);
        return mapper.map(grade, GradeResponseDto.class);
    }

    private Grade findGradeEntityById(final Long gradeId) {
        return gradeRepository.findById(gradeId).orElseThrow(
                EntityNotFoundException::new);
    }
}
