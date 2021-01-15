package ru.napoleonit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PollsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollsManagementApplication.class, args);
	}
}
