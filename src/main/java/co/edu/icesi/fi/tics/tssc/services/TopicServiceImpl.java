package co.edu.icesi.fi.tics.tssc.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.dao.ITsscTopicDAO;
import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicException;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repositories.ITopicRepository;

//@EnableJpaRepositories("co.edu.icesi.fi.tics.tssc.repositories")
@Service
public class TopicServiceImpl implements TopicService {

	public ITsscTopicDAO topicDao;

	@Autowired
	public TopicServiceImpl(ITsscTopicDAO topicDao) {
		this.topicDao = topicDao;

	}

	@Override
	@Transactional
	public TsscTopic saveTopic(TsscTopic nuevo) throws CapacityException, TopicException, SpringException {

		if (nuevo == null) {
			throw new TopicException();
		} else if (nuevo.getDefaultGroups() <= 0) {

			throw new CapacityException();

		} else if (nuevo.getDefaultSprints() <= 0) {

			throw new SpringException();

		} else {
			
		    topicDao.save(nuevo);
			return nuevo;

		}

	}

	@Override
	@Transactional
	public TsscTopic editTopic(TsscTopic editado) throws TopicException, CapacityException, SpringException {

		if (editado == null) {
			throw new TopicException();
			
		} else if (topicDao.findById(editado.getId()).isEmpty()) {
			
			throw new TopicException();
			
		} else if (editado.getDefaultGroups() <= 0) {

			throw new CapacityException();

		} else if (editado.getDefaultSprints() <= 0) {

			throw new SpringException();

		} else {
		    topicDao.save(editado);
		    return editado;

		}

	}

	@Override
	public Iterable<TsscTopic> findAll() {
		// TODO Auto-generated method stub
		return topicDao.findAll();
	}

	@Override
	public Optional<TsscTopic> findById(long id) {
		Optional<TsscTopic> op = Optional.of(topicDao.findById(id).get(0));
		return op;
	}

	@Override
	public void delete(TsscTopic del) {
		topicDao.delete(del);
	}

}
