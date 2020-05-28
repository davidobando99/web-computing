package repositories;

import java.util.HashMap;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import modelo.Person;

@Lazy
public class PersonRepositoryImp implements PersonRepository{

	private HashMap<String,Person> persons= new HashMap<String,Person>();

	
	
	@Override
	public Person savePerson(String firstName, String lastName) {
		// TODO Auto-generated method stub
		Person person = new Person(firstName,lastName);
		persons.put(person.getFirstName(),person);
		return person;
		
	}

	@Override
	public Person searchPerson(String name) {
		// TODO Auto-generated method stub
		return persons.get(name);
	}

	@Override
	public Person deletePerson(String name) {
		// TODO Auto-generated method stub
		return persons.remove(name);
	}

	@Override
	public void updatePerson(Person person, String firstName, String lastName) {
		// TODO Auto-generated method stub
		 person.setFirstName(firstName);
		 person.setLastName(lastName);
	}

}

	
