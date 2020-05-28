package co.edu.icesi.ci.thymeval.service;

import java.util.Optional;

import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.model.UserType;

public interface UserServiceInt {
	public void save(User user);

	public Optional<User> findById(long id);

	public Iterable<User> findAll();

	public Iterable<User> findAllPatients();

	public Iterable<User> findAllDoctors();

	public void delete(User user);

	public UserGender[] getGenders();

	public UserType[] getTypes();
}
