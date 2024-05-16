package br.edu.ifpb.easyschoolback.presentation.dtos.dashboard;

public record DashboardDataDto(
        Integer totalStudents,
        Integer totalTeachers,
        Integer totalCourses,
        Integer totalStudentsOnCurrentMonth
) {
}
