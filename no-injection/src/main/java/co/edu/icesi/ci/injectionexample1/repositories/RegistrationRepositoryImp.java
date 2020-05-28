package co.edu.icesi.ci.injectionexample1.repositories;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.injectionexample1.model.Course;
import co.edu.icesi.ci.injectionexample1.model.Registration;
import co.edu.icesi.ci.injectionexample1.model.Student;
@Repository
public class RegistrationRepositoryImp implements RegistrationRepository{


	public Registration enrolStudent(Student student, Course course, int semester)
	{
		//Dummy implementation
		return new Registration(student, course, semester);
	}
}
