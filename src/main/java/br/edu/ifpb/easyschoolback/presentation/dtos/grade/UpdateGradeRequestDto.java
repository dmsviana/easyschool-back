package br.edu.ifpb.easyschoolback.presentation.dtos.grade;

import br.edu.ifpb.easyschoolback.model.entities.types.GradeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGradeRequestDto {

    @NotNull(message = "O campo peso não pode ser nulo.")
    @Schema(description = "Peso da nota", example = "25")
    private Integer weight;

    @NotNull(message = "O campo valor não pode ser nulo.")
    @Schema(description = "Valor da nota", example = "8.5")
    private Double gradeValue;

    @NotNull(message = "O campo tipo não pode ser vazio.")
    @Schema(description = "Tipo da nota", example = "EXAM")
    private GradeType type;


}
