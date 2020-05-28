package repositories;

import modelo.Person;

public interface PersonRepository {
	
	public Person savePerson(String firstName, String lastName);
	public Person searchPerson(String name);
	public Person deletePerson(String name);
	public void updatePerson(Person person,String fistName, String lastName);

}
