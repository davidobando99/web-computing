package co.edu.icesi.ci.thymeval.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.model.UserType;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByName(String name);
	
	List<User> findByType(UserType patient);

}
