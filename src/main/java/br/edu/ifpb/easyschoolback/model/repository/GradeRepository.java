package br.edu.ifpb.easyschoolback.model.repository;

import br.edu.ifpb.easyschoolback.model.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {


}
