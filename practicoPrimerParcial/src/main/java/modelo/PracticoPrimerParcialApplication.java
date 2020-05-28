package modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;



import repositories.PersonRepository;
import repositories.PersonRepositoryImp;
import services.PersonService;
import services.PersonServiceImp;

@SpringBootApplication
public class PracticoPrimerParcialApplication {
	@Autowired
	private  PersonService person;

	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(PracticoPrimerParcialApplication.class, args);
		//ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		PersonService person = context.getBean(PersonService.class);
		person.savePerson("Laura", "Obando");
	}
	
//	@Bean
//	public CommandLineRunner command() {
//		
//		
//		return (args) -> {
//			person.savePerson("Laura", "Obando");
//			//person = new PersonServiceImp(personRepository);
//		};
//
//	}
	

}
