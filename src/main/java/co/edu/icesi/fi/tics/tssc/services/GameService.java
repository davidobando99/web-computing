package co.edu.icesi.fi.tics.tssc.services;

import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicException;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface GameService {

	public TsscGame saveGameWithTopic(TsscGame nuevo, long id) throws CapacityException, TopicException, SpringException, GameException;
	public TsscGame saveGame(TsscGame nuevo) throws CapacityException, GameException, SpringException;
	public TsscGame editGame(TsscGame editado) throws GameException, CapacityException, SpringException;
	public Iterable<TsscGame> findAll();
	public Optional<TsscGame> findById(long id);
	public void delete(TsscGame del);
	//Refactor
	public TsscGame saveGameWithTopic2(TsscTopic game) throws Exception;
}
