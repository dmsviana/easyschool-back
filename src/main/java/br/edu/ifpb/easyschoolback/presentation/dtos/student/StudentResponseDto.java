package br.edu.ifpb.easyschoolback.presentation.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String parentName;
    private String parentPhone;
    private String cpf;
    private String email;
    private String phone;
    private String registration;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}