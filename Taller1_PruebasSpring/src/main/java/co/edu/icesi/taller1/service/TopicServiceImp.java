package co.edu.icesi.taller1.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.taller1.model.TsscTopic;
import co.edu.icesi.taller1.repository.TopicRepository;
@Service
public class TopicServiceImp implements TopicService{

	public TopicRepository topicRepository;
	
	@Autowired
	public TopicServiceImp(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}
	@Override
	public TsscTopic saveTopic(TsscTopic topic) throws Exception {
		if(topic ==null) {
			throw new Exception("El tema no puede ser nulo");
		}
		else if((topic.getDefaultGroups()<=0) ||(topic.getDefaultSprints()<=0)) {
			throw new Exception("Sprints o Groups no pueden ser <=0");
		}
		else {
		topicRepository.save(topic);
		
		return topic;
		}
	
		
	}

	
	
	
	@Override
	public TsscTopic editTopic(TsscTopic topicSet)  throws Exception{
		TsscTopic topic = topicRepository.findById(topicSet.getId()).get();
		
		if(topic ==null) {
			throw new Exception("El tema no puede ser nulo");
		}
		else if((topicSet.getDefaultGroups()<=0) ||(topicSet.getDefaultSprints()<=0)) {
			throw new Exception("Sprints o Groups no pueden ser <=0");
		}
		else {

		topicRepository.save(topicSet);
			
		return topic;
		}
		
		
	}	
		
	
	public TopicRepository getTopicRepository() {
		return topicRepository;
	}
	public void setTopicRepository(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}
	
	public int sizeTopics() {
		Iterable<TsscTopic> topics =topicRepository.findAll();
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
