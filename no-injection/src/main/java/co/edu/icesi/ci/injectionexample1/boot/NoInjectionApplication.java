package co.edu.icesi.ci.injectionexample1.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import co.edu.icesi.ci.injectionexample1.repositories.CourseRepository;
import co.edu.icesi.ci.injectionexample1.repositories.RegistrationRepository;
import co.edu.icesi.ci.injectionexample1.repositories.StudentRepository;
import co.edu.icesi.ci.injectionexample1.service.RegistrationService;
import co.edu.icesi.ci.injectionexample1.service.RegistrationServiceImp;

@SpringBootApplication
@ComponentScan(value = "co.edu.icesi.ci.injectionexample1.repositories")
public class NoInjectionApplication {
	
	private static RegistrationService registration;

		
	
	public static void main(String[] args) {
		
		SpringApplication.run(NoInjectionApplication.class, args);
		
		registration.enrolStudent("11","101",192);
		
	}
	@Bean
	public CommandLineRunner dummy(StudentRepository studentRepository, CourseRepository courseRepository,
			RegistrationRepository registrationRepository) {
		
		return (args) -> {
			registration = new RegistrationServiceImp(studentRepository,courseRepository,registrationRepository);
		};

	}
	
	

}

