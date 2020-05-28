package co.edu.icesi.ci.injectionexample1.repositories;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.injectionexample1.model.Student;
@Repository
public class StudentRepositoryImp implements StudentRepository{

	public Student getStudent (String id)
	{
		// Dummy return: use a constructor to set the id of a new student
		return new Student(id);
	}
}
