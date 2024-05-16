package br.edu.ifpb.easyschoolback.business.service;


import br.edu.ifpb.easyschoolback.presentation.dtos.dashboard.DashboardDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DashboardService {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;


    public DashboardDataDto getDashboardData() {
        log.info("Getting dashboard data");

        Integer totalStudents = countTotalStudents();
        Integer totalTeachers = countTotalTeachers();
        Integer totalCourses = countTotalCourses();
        Integer totalStudentsOnCurrentMonth = countStudentsOnCurrentMonth();

        log.info("Dashboard data retrieved: {}", totalStudents, totalTeachers, totalCourses, totalStudentsOnCurrentMonth);
        return new DashboardDataDto(totalStudents, totalTeachers, totalCourses, totalStudentsOnCurrentMonth);
    }

    private Integer countStudentsOnCurrentMonth() {
        return studentService.countStudentsOnCurrentMonth();
    }

    private Integer countTotalStudents() {
        return studentService.countTotalStudents();
    }

    private Integer countTotalTeachers() {
        return teacherService.countTotalTeachers();
    }

    private Integer countTotalCourses() {
        return courseService.countTotalCourses();
    }

    private Integer countStudentsByCourseId(final Long courseId) {
        return courseService.countStudentsByCourseId(courseId);
    }




}
