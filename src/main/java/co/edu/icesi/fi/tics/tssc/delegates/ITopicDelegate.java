package co.edu.icesi.fi.tics.tssc.delegates;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface ITopicDelegate {
	
	
	public TsscTopic getTopic(long id)  throws Exception;
	public Iterable<TsscTopic> getTopics();
	public TsscTopic addTopic(TsscTopic newTopic);
	public void deleteTopic(TsscTopic topic);

}
