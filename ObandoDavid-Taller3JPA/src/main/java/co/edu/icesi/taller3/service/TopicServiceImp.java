package co.edu.icesi.taller3.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.taller3.dao.TsscTopicDao;
import co.edu.icesi.taller3.model.TsscTopic;
import co.edu.icesi.taller3.repository.TopicRepository;
@Service
public class TopicServiceImp implements TopicService{

	public TsscTopicDao topicDao;
	
	@Autowired
	public TopicServiceImp(TsscTopicDao topicDao) {
		this.topicDao = topicDao;
	}
	@Override
	@Transactional
	public TsscTopic saveTopic(TsscTopic topic) throws Exception {
		if(topic ==null) {
			throw new Exception("El tema no puede ser nulo");
		}
		else if((topic.getDefaultGroups()<=0) ||(topic.getDefaultSprints()<=0)) {
			throw new Exception("Sprints o Groups no pueden ser <=0");
		}
		else {
		topicDao.save(topic);
		
		return topic;
		}
	
		
	}

	
	
	
	@Override
	@Transactional
	public TsscTopic editTopic(TsscTopic topicSet)  throws Exception{
		TsscTopic topic = topicDao.findById(topicSet.getId());
		
		if(topic ==null) {
			throw new Exception("El tema no puede ser nulo");
		}
		else if((topicSet.getDefaultGroups()<=0) ||(topicSet.getDefaultSprints()<=0)) {
			throw new Exception("Sprints o Groups no pueden ser <=0");
		}
		else {

		topicDao.save(topicSet);
			
		return topic;
		}
		
		
	}	
		
	
	public TsscTopicDao getTopicRepository() {
		return topicDao;
	}
	public void setTopicRepository(TsscTopicDao topicRepository) {
		this.topicDao = topicRepository;
	}
	 public Iterable<TsscTopic> findAll() {
		return topicDao.findAll();
	}
	 
	 public Optional<TsscTopic> findById(Long id) {
			return Optional.of(topicDao.findById(id));
		}
	 
	 public void delete(TsscTopic topic) {
			 topicDao.delete(topic);
		}
	public int sizeTopics() {
		Iterable<TsscTopic> topics =topicDao.findAll();
		Iterator<TsscTopic> topicsSaved = topics.iterator();
		int size=-1;
		while(topicsSaved.hasNext()) {
			topicsSaved.next();
			size++;
			System.out.println(size);
		}
		return size;
	}
	

}
