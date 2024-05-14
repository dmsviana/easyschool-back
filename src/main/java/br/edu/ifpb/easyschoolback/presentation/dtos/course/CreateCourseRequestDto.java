package br.edu.ifpb.easyschoolback.presentation.dtos.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.DayOfWeek;
import java.util.List;

public record CreateCourseRequestDto(

        @NotBlank(message = "O campo nome do curso não pode ser vazio.")
        String name,

        @NotBlank(message = "O campo descrição do curso não pode ser vazio.")
        String description,

        @NotNull(message = "O campo capacidade máxima não pode ser vazio.")
        @Positive(message = "O campo capacidade máxima deve ser maior que zero.")
        Integer maxCapacity,

        @NotNull(message = "O campo idade mínima não pode ser vazio.")
        @Positive(message = "O campo idade mínima deve ser maior que zero.")
        Integer minAge,

        @NotNull(message = "O campo idade máxima não pode ser vazio.")
        @Positive(message = "O campo idade máxima deve ser maior que zero.")
        Integer maxAge,

        @NotNull(message = "O campo dias da semana não pode ser vazio.")
        List<DayOfWeek> daysOfWeek
) {
}