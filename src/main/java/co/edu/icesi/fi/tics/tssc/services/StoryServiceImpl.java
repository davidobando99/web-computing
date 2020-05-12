package co.edu.icesi.fi.tics.tssc.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.dao.ITsscGameDAO;
import co.edu.icesi.fi.tics.tssc.dao.ITsscStoryDAO;
import co.edu.icesi.fi.tics.tssc.exceptions.BusinessValueException;
import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameException;
import co.edu.icesi.fi.tics.tssc.exceptions.InitialSprintException;
import co.edu.icesi.fi.tics.tssc.exceptions.PriorityException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.StoryException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicException;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.repositories.IGameRepository;
import co.edu.icesi.fi.tics.tssc.repositories.IStoryRepository;

@Service
public class StoryServiceImpl implements StoryService {

	private ITsscGameDAO gameDao;

	private ITsscStoryDAO storyDao;

	@Autowired
	public StoryServiceImpl(ITsscGameDAO gameDao, ITsscStoryDAO storyDao) {
		// TODO Auto-generated constructor stub
		this.gameDao = gameDao;
		this.storyDao = storyDao;
	}

	@Override
	@Transactional
	public TsscStory saveStory(TsscStory nuevo, long id)
			throws StoryException, GameException, BusinessValueException, InitialSprintException, PriorityException {
		// TODO Auto-generated method stub
		if (nuevo == null) {
			throw new StoryException();
		} else if (gameDao.findById(id).isEmpty()) {
			throw new GameException();

		} else if (nuevo.getBusinessValue().compareTo(new BigDecimal(0)) == 0
				|| nuevo.getBusinessValue().compareTo(new BigDecimal(0)) == -1) {
			throw new BusinessValueException();

		} else if (nuevo.getInitialSprint().compareTo(new BigDecimal(0)) == 0
				|| nuevo.getInitialSprint().compareTo(new BigDecimal(0)) == -1) {
			throw new InitialSprintException();

		} else if (nuevo.getPriority().compareTo(new BigDecimal(0)) == 0
				|| nuevo.getPriority().compareTo(new BigDecimal(0)) == -1) {
			throw new PriorityException();

		} else {
			nuevo.setTsscGame(gameDao.findById(id).get(0));
		    storyDao.save(nuevo);
		    return nuevo;
		}
	}

	@Override
	public TsscStory editStory(TsscStory editado) throws StoryException {
		if (editado == null) {
			throw new StoryException();
		} else if (storyDao.findById(editado.getId()).get(0) == null) {
			throw new StoryException();
		} else {

			storyDao.save(editado);
			return editado;

		}
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return storyDao.findAll();
	}

	@Override
	public Optional<TsscStory> findById(long id) {
		 Optional<TsscStory> op = Optional.of(storyDao.findById(id).get(0));		 
		 return op;
	}

	@Override
	public void delete(TsscStory del) {
		storyDao.delete(del);
	}

}
