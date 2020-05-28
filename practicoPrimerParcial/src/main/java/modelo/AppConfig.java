package modelo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import repositories.PersonRepository;
import repositories.PersonRepositoryImp;
import services.PersonServiceImp;

@Configuration
public class AppConfig {
	@Bean
	public PersonRepositoryImp personRepository() {
		System.out.println("crea bean");
		return new PersonRepositoryImp();
	}
	@Bean
	public PersonServiceImp personService(PersonRepositoryImp personRepository) {
		return new PersonServiceImp(personRepository);
	}

}
