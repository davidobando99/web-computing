package co.edu.icesi.fi.tics.tssc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
public interface IAdminRepository extends CrudRepository<TsscAdmin, Long>{
	
	TsscAdmin findByUsername(String user);
}
