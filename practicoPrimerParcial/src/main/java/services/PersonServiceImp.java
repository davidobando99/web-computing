package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import lombok.extern.log4j.Log4j2;
import modelo.Person;
import repositories.PersonRepository;
import repositories.PersonRepositoryImp;


@Log4j2
public class PersonServiceImp implements PersonService{
	
	private PersonRepository personRepository;
	
	@Autowired
	public PersonServiceImp(PersonRepository personRepository){
		this.personRepository = personRepository;
	}
	
	public void savePerson(String firstName, String lastName) {
		Person person = personRepository.savePerson(firstName,lastName);
		log.info("Person " + person.getFirstName()+ " registered successfully ");
	}

}
