package br.edu.ifpb.easyschoolback.presentation.dtos.student;

public record UpdateStudentRequestDto(
        String parentName,
        String parentPhone,
        String email,
        String phone
) { }
