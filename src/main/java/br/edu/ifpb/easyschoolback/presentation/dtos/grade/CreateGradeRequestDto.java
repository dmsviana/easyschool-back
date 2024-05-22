package br.edu.ifpb.easyschoolback.presentation.dtos.grade;

import br.edu.ifpb.easyschoolback.model.entities.GradeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGradeRequestDto(
        @NotBlank(message = "O campo abreviação não pode ser vazio.")
        @Schema(description = "Abreviação da nota", example = "E1, P1, H1")
        String abbreviation,

        @NotBlank(message = "O campo descrição não pode ser vazio.")
        @Schema(description = "Descrição da nota", example = "Nota obtida através do exame 1.")
        String description,

        @NotNull(message = "O campo peso não pode ser nulo.")
        @Schema(description = "Peso da nota", example = "25")
        Integer weight,

        @NotNull(message = "O campo valor não pode ser nulo.")
        @Schema(description = "Valor da nota", example = "8.5")
        Double value,

        @NotNull(message = "O campo tipo não pode ser vazio.")
        @Schema(description = "Tipo da nota", example = "EXAME, PROVA, TRABALHO")
        GradeType type

) {
}