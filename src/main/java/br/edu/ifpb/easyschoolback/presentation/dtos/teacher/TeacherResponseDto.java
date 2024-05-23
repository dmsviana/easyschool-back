package br.edu.ifpb.easyschoolback.presentation.dtos.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String phone;
    private String registration;
    private BigDecimal salary;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}