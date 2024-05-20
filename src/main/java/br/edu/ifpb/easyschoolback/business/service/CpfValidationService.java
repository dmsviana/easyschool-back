package br.edu.ifpb.easyschoolback.business.service;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.entities.Teacher;
import br.edu.ifpb.easyschoolback.model.repository.StudentRepository;
import br.edu.ifpb.easyschoolback.model.repository.TeacherRepository;
import br.edu.ifpb.easyschoolback.model.repository.exception.EntityAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CpfValidationService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    public void validateCpf(final String cpf) {
        log.info("Validating CPF: {}", cpf);

        Optional<Student> studentExists = studentRepository.findByCpf(cpf);
        Optional<Teacher> teacherExists = teacherRepository.findByCpf(cpf);

        if(studentExists.isPresent() || teacherExists.isPresent()) {
            throw new EntityAlreadyExistsException();
        }
    }
}
