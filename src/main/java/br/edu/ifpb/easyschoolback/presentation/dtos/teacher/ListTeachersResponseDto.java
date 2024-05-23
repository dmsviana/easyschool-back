package br.edu.ifpb.easyschoolback.presentation.dtos.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListTeachersResponseDto {
    private List<TeacherResponseDto> teachers;
}