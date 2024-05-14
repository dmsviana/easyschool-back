package br.edu.ifpb.easyschoolback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class EasyschoolBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyschoolBackApplication.class, args);
	}


}
