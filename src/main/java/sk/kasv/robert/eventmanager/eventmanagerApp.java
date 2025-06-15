package sk.kasv.robert.eventmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class eventmanagerApp {

	public static void main(String[] args) {
		SpringApplication.run(eventmanagerApp.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {

		};
	}




}
