package co.edu.icesi.ci.injectionexample1.repositories;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.injectionexample1.model.Course;

@Repository
public class CourseRepositoryImp implements CourseRepository{
	
	
	public Course getCourse (String id)
	{
		// Dummy return: use a constructor to set the id of a new Course
		return new Course(id,"");
	}
}
