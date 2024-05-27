package br.edu.ifpb.easyschoolback.usecases;

import br.edu.ifpb.easyschoolback.model.entities.Student;
import br.edu.ifpb.easyschoolback.model.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public record InsertStudentUser(StudentRepository userRepository) {

    private static final String ADMIN = "admin@easyschool.com";

    public void insertStudentUser() {
        if (userRepository.findByEmail(ADMIN).isEmpty()) {
            log.debug("Administrator user not found, creating...");
            final var user = new Student();
            user.setFirstName("Administrator");
            user.setLastName("Teste");
            user.setEmail(ADMIN);
            user.setRegistration("202423515");
            user.setCpf("103.800.364-44");
            user.setPhone("83996586204");
            user.setDateOfBirth(LocalDate.of(1998, 7, 17));
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        } else {
            log.info("insertAdminUser: admin user already exists");
        }
    }
}