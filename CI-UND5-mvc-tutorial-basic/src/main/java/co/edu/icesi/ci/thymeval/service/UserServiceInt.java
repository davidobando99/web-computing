package co.edu.icesi.ci.thymeval.service;

import java.util.Optional;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.model.UserType;

public interface UserServiceInt {
	public void save(UserApp user);

	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllPatients();

	public Iterable<UserApp> findAllDoctors();

	public void delete(UserApp user);

	public UserGender[] getGenders();

	public UserType[] getTypes();
}
