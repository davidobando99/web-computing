package co.edu.icesi.taller2.service;


import java.util.Optional;

import co.edu.icesi.taller2.model.TsscGame;
import co.edu.icesi.taller2.model.TsscStory;

public interface StoryService {
	
	public TsscStory saveStory(TsscStory story,TsscGame game)throws Exception;
	public TsscStory editStory(TsscStory storyEdit, TsscGame game)throws Exception;
	public Iterable<TsscStory> findAll();
	 public Optional<TsscStory> findById(Long id);
	 
	 public void delete(TsscStory story);
	 public TsscGame getGame(TsscStory story);
	 
}
