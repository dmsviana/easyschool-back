package br.edu.ifpb.easyschoolback.presentation.dtos.teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TeacherResponseDto(
        Long id,
        String firstName,
        String lastName,
        String cpf,
        String email,
        String phone,
        String registration,
        BigDecimal salary,
        LocalDate dateOfBirth,
        LocalDate registrationDate,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
