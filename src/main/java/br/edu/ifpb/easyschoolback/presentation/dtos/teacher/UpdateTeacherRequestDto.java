package br.edu.ifpb.easyschoolback.presentation.dtos.teacher;

import java.math.BigDecimal;

public record UpdateTeacherRequestDto(
        String email,
        String phone,
        BigDecimal salary
) {
}
