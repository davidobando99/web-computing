package co.edu.icesi.taller3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscTopic;

@Repository
public interface GameRepository extends CrudRepository<TsscGame,Long>{

}
