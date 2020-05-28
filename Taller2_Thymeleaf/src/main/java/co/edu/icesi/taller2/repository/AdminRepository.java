package co.edu.icesi.taller2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import co.edu.icesi.taller2.model.TsscAdmin;

@Repository
public interface AdminRepository extends CrudRepository<TsscAdmin,Long>{
	
	TsscAdmin findByUsername (String name);

}
