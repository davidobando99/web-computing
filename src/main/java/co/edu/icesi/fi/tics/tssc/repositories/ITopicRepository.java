package co.edu.icesi.fi.tics.tssc.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
public interface ITopicRepository extends CrudRepository<TsscTopic, Long>{
	
}
