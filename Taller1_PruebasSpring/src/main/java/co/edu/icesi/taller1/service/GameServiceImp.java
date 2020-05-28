package co.edu.icesi.taller1.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.taller1.model.TsscGame;
import co.edu.icesi.taller1.model.TsscStory;
import co.edu.icesi.taller1.model.TsscTimecontrol;
import co.edu.icesi.taller1.model.TsscTopic;
import co.edu.icesi.taller1.repository.GameRepository;
import co.edu.icesi.taller1.repository.TopicRepository;
@Service
public class GameServiceImp implements GameService{
	
	private GameRepository gameRepo;
	private TopicRepository topicRepo;
	
	@Autowired
	public GameServiceImp(GameRepository gameRepo, TopicRepository topicRepo) {
		
		this.gameRepo = gameRepo;
		this.topicRepo = topicRepo;
	}

	@Override
	public TsscGame saveGame(TsscGame game,TsscTopic topic) throws Exception {
		if(topic==null) {
			if(game ==null) {
				throw new Exception("El juego no puede ser nulo");
			}
			else if((game.getNGroups()<=0) ||(game.getNSprints()<=0)) {
				throw new Exception("Groups o Sprints no pueden ser <=0");
			}
			else {
			gameRepo.save(game);
			
			return game;
			}
		}else {
			TsscTopic founded = topicRepo.findById(topic.getId()).get();
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
			gameRepo.save(game);
			
			return game;
		}
	}
	}

	

	@Override
	public TsscGame editGame(TsscGame gameEdit, TsscTopic topic) throws Exception {
		TsscGame game = gameRepo.findById(gameEdit.getId()).get();
		if(topic==null) {
			if(game ==null) {
				throw new Exception("El juego no existe");
			}
			else if((gameEdit.getNGroups()<=0) ||(gameEdit.getNSprints()<=0)) {
				throw new Exception("Groups o Sprints no pueden ser <=0");
			}
			else {
			gameRepo.save(gameEdit);
			
			return gameEdit;
			}
		}else {
			
			TsscTopic founded = topicRepo.findById(topic.getId()).get();
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
			gameRepo.save(gameEdit);
			
			return gameEdit;
			}
		}
		
	}

	@Override
	public TsscGame saveGame2(TsscGame game, TsscTopic topic) throws Exception {
		
			if(game ==null) {
				throw new Exception("El juego no puede ser nulo");
			}
			else {
				TsscTopic founded = topicRepo.findById(topic.getId()).get();
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
					gameRepo.save(game);
					}
					
					return game;
					}
				}
			}
	
	public int sizeGames() {
		Iterable<TsscGame> games =gameRepo.findAll();
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
	
	


