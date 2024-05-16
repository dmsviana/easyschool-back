package br.edu.ifpb.easyschoolback.model.repository;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Optional<Student> findByEmail(final String email);

    @Query("SELECT s FROM Student s WHERE s.cpf = :cpf")
    Optional<Student> findByCpf(final String cpf);

    @Query("SELECT s FROM Student s WHERE s.registration = :registration")
    Optional<Student> findByRegistration(final String registration);

    @Query("SELECT COUNT(s) FROM Student s WHERE MONTH(s.createdAt) = MONTH(CURRENT_DATE)")
    Integer countStudentsOnCurrentMonth();

    @Query("SELECT COUNT(s) FROM Student s")
    Integer countTotalStudents();


}
