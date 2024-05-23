package br.edu.ifpb.easyschoolback.presentation.dtos.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeacherRequestDto {

    @NotBlank(message = "O campo primeiro nome não pode ser vazio.")
    private String firstName;

    @NotBlank(message = "O campo sobrenome não pode ser vazio.")
    private String lastName;

    @NotBlank(message = "O campo CPF não pode ser vazio.")
    private String cpf;

    @NotBlank(message = "O campo email não pode ser vazio.")
    private String email;

    @NotBlank(message = "O campo senha não pode ser vazio.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;

    @NotNull(message = "O campo salário não pode ser nulo.")
    private BigDecimal salary;

    @NotBlank(message = "O campo telefone não pode ser vazio.")
    private String phone;

    @NotBlank(message = "O campo matrícula não pode ser vazio.")
    private String registration;

    @NotNull(message = "O campo data de nascimento não pode ser nulo.")
    private LocalDate dateOfBirth;
}