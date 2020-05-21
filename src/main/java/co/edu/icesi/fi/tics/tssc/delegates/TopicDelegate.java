package co.edu.icesi.fi.tics.tssc.delegates;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public class TopicDelegate implements ITopicDelegate{
	
	public final static String SERVER = "http://localhost:8080/";
	RestTemplate rest;
	public TopicDelegate(RestTemplate rest) {
		rest = new RestTemplate();
	}
	@Override
	public TsscTopic getTopic(long id) throws Exception{
		TsscTopic topic = rest.getForObject(SERVER+ "topics/" +id, TsscTopic.class);
		if(topic==null) {
			throw new Exception("topic is null");
		}
		return topic;
	}
	@Override
	public Iterable<TsscTopic> getTopics() {
		TsscTopic[] topics = rest.getForObject(SERVER+ "topics", TsscTopic[].class);
		List<TsscTopic> at;
		try {
			at = Arrays.asList(topics);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public TsscTopic addTopic(TsscTopic newTopic) {
		TsscTopic topic = rest.postForEntity(SERVER + "topics", newTopic, TsscTopic.class).getBody();
		if (topic == null) {
			throw new IllegalArgumentException("topic is null");
		}
		return topic;
	}
	@Override
	public void deleteTopic(TsscTopic topic) {
		if (topic == null) {
			throw new IllegalArgumentException("topic is null");
		}
		rest.delete(SERVER + "topics/" +topic.getId());
		
	}

}
