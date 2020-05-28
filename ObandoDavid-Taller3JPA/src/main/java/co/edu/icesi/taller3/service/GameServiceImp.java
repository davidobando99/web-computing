package co.edu.icesi.taller3.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.taller3.dao.TsscGameDao;
import co.edu.icesi.taller3.dao.TsscTopicDao;
import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.model.TsscTimecontrol;
import co.edu.icesi.taller3.model.TsscTopic;
import co.edu.icesi.taller3.repository.GameRepository;
import co.edu.icesi.taller3.repository.TopicRepository;
@Service
public class GameServiceImp implements GameService{
	
	private TsscGameDao gameDao;
	private TsscTopicDao topicDao;
	
	@Autowired
	public GameServiceImp(TsscGameDao gameDao, TsscTopicDao topicDao) {
		
		this.gameDao = gameDao;
		this.topicDao = topicDao;
	}

	@Override
	@Transactional
	public TsscGame saveGame(TsscGame game,TsscTopic topic) throws Exception {
		if(topic==null) {
			if(game ==null) {
				throw new Exception("El juego no puede ser nulo");
			}
			else if((game.getNGroups()<=0) ||(game.getNSprints()<=0)) {
				throw new Exception("Groups o Sprints no pueden ser <=0");
			}
			else {
			gameDao.save(game);
			
			return game;
			}
		}else {
			TsscTopic founded = topicDao.findById(topic.getId());
			if(game ==null) {
				throw new Exception("El juego no puede ser nulo");
			}
			if(founded ==null) {
				throw new Exception("El tema no existe");
			}
			else if((game.getNGroups()<=0) ||(game.getNSprints()<=0)) {
				throw new Exception("Groups o Sprints no pueden ser <=0");
			}
			else {
			game.setTsscTopic(topic);
			gameDao.save(game);
			
			return game;
		}
	}
	}

	

	@Override
	@Transactional
	public TsscGame editGame(TsscGame gameEdit, TsscTopic topic) throws Exception {
		TsscGame game = gameDao.findById(gameEdit.getId());
		if(topic==null) {
			if(game ==null) {
				throw new Exception("El juego no existe");
			}
			else if((gameEdit.getNGroups()<=0) ||(gameEdit.getNSprints()<=0)) {
				throw new Exception("Groups o Sprints no pueden ser <=0");
			}
			else {
			gameDao.save(gameEdit);
			
			return gameEdit;
			}
		}else {
			
			TsscTopic founded = topicDao.findById(topic.getId());
			if(gameEdit ==null) {
				throw new Exception("El juego no existe");
			}
			else if(gameEdit ==null) {
				throw new Exception("El tema no existe");
			}
			else if((gameEdit.getNGroups()<=0) ||(gameEdit.getNSprints()<=0)) {
				throw new Exception("Groups o Sprints no pueden ser <=0");
			}
			else {
				gameEdit.setTsscTopic(topic);
			gameDao.save(gameEdit);
			
			return gameEdit;
			}
		}
		
	}

	@Override
	@Transactional
	public TsscGame saveGame2(TsscGame game, TsscTopic topic) throws Exception {
		
			if(game ==null) {
				throw new Exception("El juego no puede ser nulo");
			}
			else {
				TsscTopic founded = topicDao.findById(topic.getId());
				if(founded ==null) {
					throw new Exception("El tema no existe");
				}
				else if((game.getNGroups()<=0) ||(game.getNSprints()<=0)) {
					throw new Exception("Groups o Sprints no pueden ser <=0");
				}
				else {
					
					if(topic.getTsscStories()==null|| topic.getTsscTimeControls()==null)
						throw new Exception("El tema debe tener asociado una lista de historias y cronogramas");
					else {
					List<TsscStory> storiesCopy = topic.getTsscStories();
					List<TsscTimecontrol> timesCopy = topic.getTsscTimeControls();
					
					game.setTsscTopic(topic);
					game.setTsscStories(storiesCopy);
					game.setTsscTimecontrol(timesCopy);
					gameDao.save(game);
					}
					
					return game;
					}
				}
			}
	 public Iterable<TsscGame> findAll() {
		return gameDao.findAll();
	}
	 
	 public Optional<TsscGame> findById(Long id) {
			return Optional.of(gameDao.findById(id));
		}
	 
	 public void delete(TsscGame game) {
		 gameDao.delete(game);
		}
	 public List<TsscStory> getStories(TsscGame game) {
		 return game.getTsscStories();
	 }
	public int sizeGames() {
		Iterable<TsscGame> games =gameDao.findAll();
		Iterator<TsscGame> gamesSaved = games.iterator();
		int size=-1;
		while(gamesSaved.hasNext()) {
			
			gamesSaved.next() ;
			size++;
			System.out.println(size);
			
		}
		return size;
	}
	
	}
	
	


