package br.edu.ifpb.easyschoolback.model.repository;

import br.edu.ifpb.easyschoolback.model.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT s FROM Teacher s WHERE s.email = :email")
    Optional<Teacher> findByEmail(final String email);

    @Query("SELECT s FROM Teacher s WHERE s.cpf = :cpf")
    Optional<Teacher> findByCpf(final String cpf);

    @Query("SELECT s FROM Teacher s WHERE s.registration = :registration")
    Optional<Teacher> findByRegistration(final String registration);

    @Query("SELECT COUNT(s) FROM Teacher s WHERE MONTH(s.createdAt) = MONTH(CURRENT_DATE)")
    Integer countTeachersOnCurrentMonth();

    @Query("SELECT COUNT(s) FROM Teacher s")
    Integer countTotalTeachers();

    @Query("SELECT COUNT(s) FROM Teacher s WHERE s.salary > :salary")
    Integer countTeachersWithSalaryGreaterThan(final BigDecimal salary);


}
