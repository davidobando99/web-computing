package co.edu.icesi.fi.ci.junit5exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.edu.icesi.fi.ci.junit5exercise.repository.OrderRepository;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderServiceImp;
@SpringBootApplication
public class Junit5AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(Junit5AdvancedApplication.class, args);
	}
}
