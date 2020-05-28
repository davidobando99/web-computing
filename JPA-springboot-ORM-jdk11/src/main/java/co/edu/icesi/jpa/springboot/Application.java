package co.edu.icesi.jpa.springboot;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.edu.icesi.jpa.springboot.model.TAlumno;
import co.edu.icesi.jpa.springboot.crud.AlumnosRepository;
import co.edu.icesi.jpa.springboot.crud.MateriasRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(AlumnosRepository alumnosRepository, MateriasRepository materiasRepository) {
		return (args) -> {
			alumnosRepository.save(new TAlumno("Jack", "Bauer"));
			alumnosRepository.save(new TAlumno("Chloe", "O'Brian"));
			alumnosRepository.save(new TAlumno("Kim", "Bauer"));
			alumnosRepository.save(new TAlumno("David", "Palmer"));
			alumnosRepository.save(new TAlumno("Michelle", "Dessler"));

			// fetch all alumnos
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (TAlumno alumno : alumnosRepository.findAll()) {
				log.info(alumno.toString());
			}
			log.info(""+alumnosRepository.findAll());

			// fetch all customers
						log.info("alumnos found with findAll():");
						log.info("-------------------------------");
						for (TAlumno alumno : alumnosRepository.findAll()) {
							log.info(alumno.toString());
						}
						log.info("");

						// fetch an individual alumno by codigo
						alumnosRepository.findById(1)
							.ifPresent(customer -> {
								log.info("Customer found with findById(1L):");
								log.info("--------------------------------");
								log.info(customer.toString());
								log.info("");
							});

						// fetch customers by apellidos
						log.info("Alumno found with findByApellidos('Bauer'):");
						log.info("--------------------------------------------");
						alumnosRepository.findByApellidos("Bauer").forEach(bauer -> {
							log.info(bauer.toString());
						});
						// for (TAlumno bauer : alumnosRepository.findByApellidos("Bauer")) {
						// 	log.info(bauer.toString());
						// }
						log.info("");
		};
	}

}
