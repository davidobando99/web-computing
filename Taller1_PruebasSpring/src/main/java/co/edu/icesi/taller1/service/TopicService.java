package co.edu.icesi.taller1.service;

import java.util.Optional;

import co.edu.icesi.taller1.model.TsscTopic;

public interface TopicService {
	
	public TsscTopic saveTopic(TsscTopic topic)  throws Exception;
	
	public TsscTopic editTopic(TsscTopic topicSet) throws Exception;

}
