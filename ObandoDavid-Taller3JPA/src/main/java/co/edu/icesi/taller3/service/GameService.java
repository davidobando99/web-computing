package co.edu.icesi.taller3.service;

import java.util.List;
import java.util.Optional;

import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.model.TsscTopic;

public interface GameService {
	
	public TsscGame saveGame(TsscGame game, TsscTopic topi)throws Exception;
	public TsscGame editGame(TsscGame gameEdit, TsscTopic topic)throws Exception;
	public TsscGame saveGame2(TsscGame game, TsscTopic topic)throws Exception;
	 public Iterable<TsscGame> findAll();
	 public Optional<TsscGame> findById(Long id);
	 public void delete(TsscGame game);
	 public List<TsscStory> getStories(TsscGame game);

}
