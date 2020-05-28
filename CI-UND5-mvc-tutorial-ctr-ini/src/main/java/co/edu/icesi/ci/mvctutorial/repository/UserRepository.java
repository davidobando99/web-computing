package co.edu.icesi.ci.mvctutorial.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.model.UserType;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByName(String name);
	
	List<User> findByType(UserType patient);

}
