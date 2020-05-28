package co.edu.icesi.taller3.service;

import java.util.Optional;

import co.edu.icesi.taller3.model.TsscTopic;

public interface TopicService {
	
	public TsscTopic saveTopic(TsscTopic topic)  throws Exception;
	
	public TsscTopic editTopic(TsscTopic topicSet) throws Exception;
	
	 public Iterable<TsscTopic> findAll();
	 
	 public Optional<TsscTopic> findById(Long id);
	 public void delete(TsscTopic topic);

}
