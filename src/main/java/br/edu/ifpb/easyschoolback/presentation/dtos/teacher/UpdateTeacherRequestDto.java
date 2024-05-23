package br.edu.ifpb.easyschoolback.presentation.dtos.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeacherRequestDto {
    private String email;
    private String phone;
    private BigDecimal salary;
}