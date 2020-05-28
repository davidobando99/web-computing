package co.edu.icesi.ci.mvctutorial.service;

import java.util.Optional;

import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.model.UserGender;
import co.edu.icesi.ci.mvctutorial.model.UserType;

public interface UserServiceImp {
	public void save(User user);

	public Optional<User> findById(long id);

	public Iterable<User> findAll();

	public Iterable<User> findAllPatients();

	public Iterable<User> findAllDoctors();

	public void delete(User user);

	public UserGender[] getGenders();

	public UserType[] getTypes();
}
