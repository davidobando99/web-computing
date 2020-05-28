package co.edu.icesi.ci.injectionexample1.repositories;

import co.edu.icesi.ci.injectionexample1.model.Course;
import co.edu.icesi.ci.injectionexample1.model.Registration;
import co.edu.icesi.ci.injectionexample1.model.Student;

public interface RegistrationRepository {

	public Registration enrolStudent(Student student, Course course, int semester);
}
