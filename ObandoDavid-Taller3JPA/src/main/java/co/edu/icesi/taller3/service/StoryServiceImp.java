package co.edu.icesi.taller3.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.taller3.dao.TsscGameDao;
import co.edu.icesi.taller3.dao.TsscStoryDao;
import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.model.TsscTopic;
import co.edu.icesi.taller3.repository.GameRepository;
import co.edu.icesi.taller3.repository.StoryRepository;

@Service
public class StoryServiceImp implements StoryService{
	
	private TsscStoryDao storyDao;
	
	private TsscGameDao gameDao;
	@Autowired
	public StoryServiceImp(TsscStoryDao storyRepo, TsscGameDao gameDao) {
		
		this.storyDao = storyRepo;
		this.gameDao = gameDao;
	}
	@Override
	@Transactional
	public TsscStory saveStory(TsscStory story, TsscGame game) throws Exception {
		TsscGame founded = gameDao.findById(game.getId());
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
		List<TsscStory> stories = new ArrayList<TsscStory>();
		game.setTsscStories(stories);
		game.addTsscStory(story);
		storyDao.save(story);
		return story;
		}
		}
	}
		
		
	@Override
	@Transactional
	public TsscStory editStory(TsscStory storyEdit, TsscGame game) throws Exception {
		TsscStory story = storyDao.findById(storyEdit.getId());
		TsscGame founded = gameDao.findById(game.getId());
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
		storyDao.save(storyEdit);
		return storyEdit;
		}
		}
	}
	
		public Iterable<TsscStory> findAll() {
			return storyDao.findAll();
			}
		 
		 public Optional<TsscStory> findById(Long id) {
				return Optional.of(storyDao.findById(id));
			}
		 
		 public void delete(TsscStory story) {
			 storyDao.delete(story);
			}
		 public TsscGame getGame(TsscStory story) {
			 return story.getTsscGame();
		 }
	public int sizeStories() {
		Iterable<TsscStory> stories =storyDao.findAll();
		Iterator<TsscStory> storiesSaved = stories.iterator();
		int size=-1;
		while(storiesSaved.hasNext()) {
			storiesSaved.next();
			size++;
			
		}
		return size;
	}
	
	
	
	
	

}
