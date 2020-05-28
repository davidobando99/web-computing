package co.edu.icesi.ci.injectionexample1.service;

import org.springframework.stereotype.Service;

import co.edu.icesi.ci.injectionexample1.model.Course;
import co.edu.icesi.ci.injectionexample1.model.Registration;
import co.edu.icesi.ci.injectionexample1.model.Student;
import co.edu.icesi.ci.injectionexample1.repositories.CourseRepository;
import co.edu.icesi.ci.injectionexample1.repositories.CourseRepositoryImp;
import co.edu.icesi.ci.injectionexample1.repositories.RegistrationRepository;
import co.edu.icesi.ci.injectionexample1.repositories.RegistrationRepositoryImp;
import co.edu.icesi.ci.injectionexample1.repositories.StudentRepository;
import co.edu.icesi.ci.injectionexample1.repositories.StudentRepositoryImp;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RegistrationServiceImp implements RegistrationService{

	private StudentRepository studentRepository = new StudentRepositoryImp();
	private CourseRepository courseRepository = new CourseRepositoryImp();
	private RegistrationRepository registrationRepository = new RegistrationRepositoryImp();

	
	
	public RegistrationServiceImp(StudentRepository studentRepository, CourseRepository courseRepository,
			RegistrationRepository registrationRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.registrationRepository = registrationRepository;

	}

	public boolean enrolStudent(String studentId, String courseId, int semester) {
		Student student = studentRepository.getStudent(studentId);
		Course course = courseRepository.getCourse(courseId);
		Registration registration = registrationRepository.enrolStudent(student, course, semester);
		log.info("Student " + registration.getStudent().getId() + " registered successfully in course "
				+ registration.getCourse().getId());
		return true;
	};
}
