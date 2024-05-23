package br.edu.ifpb.easyschoolback.presentation.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequestDto {
    private String parentName;
    private String parentPhone;
    private String email;
    private String phone;
}