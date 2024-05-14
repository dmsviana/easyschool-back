package br.edu.ifpb.easyschoolback.presentation.dtos.student;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentResponseDto(
        Long id,
        String firstName,
        String lastName,
        String parentName,
        String parentPhone,
        String cpf,
        String email,
        String phone,
        String registration,
        LocalDate dateOfBirth,
        LocalDate registrationDate,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
