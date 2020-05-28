package co.edu.icesi.taller1.service;


import co.edu.icesi.taller1.model.TsscGame;
import co.edu.icesi.taller1.model.TsscStory;

public interface StoryService {
	
	public TsscStory saveStory(TsscStory story,TsscGame game)throws Exception;
	public TsscStory editStory(TsscStory storyEdit, TsscGame game)throws Exception;

}
