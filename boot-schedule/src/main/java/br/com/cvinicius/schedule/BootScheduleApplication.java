package br.com.cvinicius.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BootScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootScheduleApplication.class, args);
	}
}