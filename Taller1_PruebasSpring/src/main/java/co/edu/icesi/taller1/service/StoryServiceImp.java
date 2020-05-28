package co.edu.icesi.taller1.service;

import java.math.BigDecimal;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.taller1.model.TsscGame;
import co.edu.icesi.taller1.model.TsscStory;
import co.edu.icesi.taller1.model.TsscTopic;
import co.edu.icesi.taller1.repository.GameRepository;
import co.edu.icesi.taller1.repository.StoryRepository;

@Service
public class StoryServiceImp implements StoryService{
	
	private StoryRepository storyRepo;
	
	private GameRepository gameRepo;
	@Autowired
	public StoryServiceImp(StoryRepository storyRepo, GameRepository gameRepo) {
		
		this.storyRepo = storyRepo;
		this.gameRepo = gameRepo;
	}
	@Override
	public TsscStory saveStory(TsscStory story, TsscGame game) throws Exception {
		TsscGame founded = gameRepo.findById(game.getId()).get();
		if(story==null) {
			throw new Exception("La historia no puede ser nula");
		}
		else {
		if(founded ==null) {
			throw new Exception("El juego no existe");
		}
		else if((story.getBusinessValue().compareTo(new BigDecimal(0))<=0) ||(story.getInitialSprint().compareTo(new BigDecimal(0))<=0)
				||(story.getPriority().compareTo(new BigDecimal(0))<=0)){
			throw new Exception("Business Value, Initial Sprint o Priority no pueden ser <=0");
		}
		else {
		story.setTsscGame(game);
		storyRepo.save(story);
		return story;
		}
		}
	}
		
		
	@Override
	public TsscStory editStory(TsscStory storyEdit, TsscGame game) throws Exception {
		TsscStory story = storyRepo.findById(storyEdit.getId()).get();
		TsscGame founded = gameRepo.findById(game.getId()).get();
		if(story==null) {
			throw new Exception("La historia no existe");
		}
		else {
		if(founded ==null) {
			throw new Exception("El juego no existe");
		}
		else if((storyEdit.getBusinessValue().compareTo(new BigDecimal(0))<=0) ||(storyEdit.getInitialSprint().compareTo(new BigDecimal(0))<=0)
				||(storyEdit.getPriority().compareTo(new BigDecimal(0))<=0)){
			throw new Exception("Business Value, Initial Sprint o Priority no pueden ser <=0");
		}
		else {
		storyEdit.setTsscGame(game);
		storyRepo.save(storyEdit);
		return storyEdit;
		}
		}
	}
	
	
	public int sizeStories() {
		Iterable<TsscStory> stories =storyRepo.findAll();
		Iterator<TsscStory> storiesSaved = stories.iterator();
		int size=-1;
		while(storiesSaved.hasNext()) {
			storiesSaved.next();
			size++;
			
		}
		return size;
	}
	
	
	
	
	

}
