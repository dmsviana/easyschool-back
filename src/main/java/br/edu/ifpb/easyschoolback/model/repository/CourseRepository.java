package br.edu.ifpb.easyschoolback.model.repository;

import br.edu.ifpb.easyschoolback.model.entities.Course;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.ListStudentsResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c.students FROM Course c WHERE c.id = :courseId")
    List<ListStudentsResponseDto> findStudentsByCourseId(final Long courseId);

    @Query("SELECT COUNT(c.students) FROM Course c WHERE c.id = :courseId")
    Integer countStudentsByCourseId(final Long courseId);

    @Modifying
    @Query(value = "DELETE FROM tb_courses_students WHERE students_id = :studentId", nativeQuery = true)
    void removeStudentFromAllCourses(@Param("studentId") Long studentId);

    @Query("SELECT COUNT(c) FROM Course c")
    Integer countTotalCourses();

    Optional<Course> findByName(final String name);

}
