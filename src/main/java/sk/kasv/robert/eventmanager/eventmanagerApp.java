package sk.kasv.robert.eventmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sk.kasv.robert.eventmanager.entity.Category;
import sk.kasv.robert.eventmanager.entity.Event;
import sk.kasv.robert.eventmanager.entity.Location;
import sk.kasv.robert.eventmanager.entity.User;
import sk.kasv.robert.eventmanager.repository.CategoryRepository;
import sk.kasv.robert.eventmanager.repository.EventRepository;
import sk.kasv.robert.eventmanager.repository.LocationRepository;
import sk.kasv.robert.eventmanager.repository.UserRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class eventmanagerApp {

	public static void main(String[] args) {
		SpringApplication.run(eventmanagerApp.class, args);
	}






}
