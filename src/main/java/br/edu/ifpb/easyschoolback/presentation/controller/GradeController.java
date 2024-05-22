package br.edu.ifpb.easyschoolback.presentation.controller;

import br.edu.ifpb.easyschoolback.business.service.GradeService;
import br.edu.ifpb.easyschoolback.presentation.controller.contract.GradeApiContract;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.CreateGradeRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.GradeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;



@RequestScope
@RestController
@RequiredArgsConstructor
public class GradeController implements GradeApiContract {

    private final GradeService gradeService;

    @Override
    public GradeResponseDto assignGradeToStudent(Long studentId, CreateGradeRequestDto gradeRequest) {
        return gradeService.assignGradeToStudent(gradeRequest, studentId);
    }
}
