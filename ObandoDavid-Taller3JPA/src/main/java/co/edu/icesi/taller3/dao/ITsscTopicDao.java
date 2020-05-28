package co.edu.icesi.taller3.dao;

import java.time.LocalDate;
import java.util.List;

import co.edu.icesi.taller3.model.TsscTopic;

public interface ITsscTopicDao {

	public void save(TsscTopic topic);
	public void update(TsscTopic topic);
	public void delete(TsscTopic topic);
	public TsscTopic findById(long id);
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String description);
	public void deleteAll();
	public List<TsscTopic> findAll();
	public List<Object[]> getTopicByGameDate(LocalDate date);
	

}
