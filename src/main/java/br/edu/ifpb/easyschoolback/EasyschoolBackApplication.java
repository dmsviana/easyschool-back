package br.edu.ifpb.easyschoolback;

import br.edu.ifpb.easyschoolback.usecases.InsertStudentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class EasyschoolBackApplication {

    private final InsertStudentUser insert;

    public static void main(String[] args) {
        SpringApplication.run(EasyschoolBackApplication.class, args);
    }

    @Bean
    InitializingBean sendDataBase() {
        return insert::insertStudentUser;
    }

}
