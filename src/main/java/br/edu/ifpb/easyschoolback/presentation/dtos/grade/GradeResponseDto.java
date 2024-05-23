package br.edu.ifpb.easyschoolback.presentation.dtos.grade;

import br.edu.ifpb.easyschoolback.model.entities.types.GradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponseDto {

    private Long id;
    private String abbreviation;
    private String description;
    private Integer weight;
    private Double gradeValue;
    private GradeType type;
    private Long studentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
