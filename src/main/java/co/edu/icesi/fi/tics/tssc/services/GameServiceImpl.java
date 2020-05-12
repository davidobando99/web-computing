package co.edu.icesi.fi.tics.tssc.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.dao.ITsscGameDAO;
import co.edu.icesi.fi.tics.tssc.dao.ITsscTopicDAO;
import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicException;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repositories.IGameRepository;
import co.edu.icesi.fi.tics.tssc.repositories.ITopicRepository;

@Service
public class GameServiceImpl implements GameService {

	private ITsscGameDAO gameDao;

	private ITsscTopicDAO topicDao;

	@Autowired
	public GameServiceImpl(ITsscGameDAO gameDao, ITsscTopicDAO topicDao) {
		// TODO Auto-generated constructor stub
		this.gameDao = gameDao;
		this.topicDao = topicDao;
	}

	@Override
	@Transactional
	public TsscGame saveGameWithTopic(TsscGame nuevo, long id)
			throws CapacityException, TopicException, SpringException, GameException {

		if (nuevo == null) {
			throw new GameException();
			
		} else if (topicDao.findById(id).isEmpty()) {
			throw new TopicException();
		} else if (nuevo.getNGroups() <= 0) {
			throw new CapacityException();
		} else if (nuevo.getNSprints() <= 0) {
			throw new SpringException();
		} else {

			nuevo.setTsscTopic(topicDao.findById(id).get(0));
		    gameDao.save(nuevo);
		    return nuevo;
		}

	}

	@Override
	@Transactional
	public TsscGame saveGame(TsscGame nuevo) throws CapacityException, GameException, SpringException {
		if (nuevo == null) {
			throw new GameException();
		} else if (nuevo.getNGroups() <= 0) {
			throw new CapacityException();
		} else if (nuevo.getNSprints() <= 0) {
			throw new SpringException();
		} else {
			gameDao.save(nuevo);
			return nuevo;
		}
	}

	@Override
	@Transactional
	public TsscGame editGame(TsscGame editado) throws GameException, CapacityException, SpringException {
		if (editado == null) {
			throw new GameException();
		} else if (gameDao.findById(editado.getId()).isEmpty()) {
			throw new GameException();
		 } else if (editado.getNGroups() <= 0) {
			throw new CapacityException();
		} else if (editado.getNSprints() <= 0) {
			throw new SpringException();
		} else {
			 gameDao.save(editado);
			return editado;
		}

	}

	// Punto d. Refactor

	@Override
	@Transactional
	public TsscGame saveGameWithTopic2(TsscTopic nuevo)
			throws CapacityException, TopicException, SpringException, GameException {

		if (nuevo == null) {
			throw new TopicException();
		} else if (nuevo.getDefaultGroups() <= 0) {
			throw new CapacityException();
		} else if (nuevo.getDefaultSprints() <= 0) {
			throw new SpringException();
		} else {
			
			//Crear juego
			
			TsscGame game = new TsscGame();
			
			//Asociar los groups y sprints al juego.
			
			game.setNGroups((int)nuevo.getDefaultGroups());
			game.setNSprints((int)nuevo.getDefaultSprints());
			
			//Copia de historias y cronogramas.
			
			List<TsscStory> listStories = nuevo.getTsscStories();
			List<TsscTimecontrol> listTimecontrol = nuevo.getTssTimecontrol();
			
			//Asociar las copias de historias y cronogramas al juego.
			
			game.setTsscStories(listStories);
			game.setTsscTimecontrol(listTimecontrol);
			
			
			gameDao.save(game);
			
			return game;
		}

	}

	@Override
	public Iterable<TsscGame> findAll() {
		return gameDao.findAll();
	}

	@Override
	public Optional<TsscGame> findById(long id) {
		Optional<TsscGame> op = Optional.of(gameDao.findById(id).get(0));
		return op;
	}

	@Override
	public void delete(TsscGame del) {
       gameDao.delete(del);
	}

}
