package co.edu.icesi.fi.tics.tssc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@Repository
public interface IGameRepository extends CrudRepository<TsscGame, Long>{

}
