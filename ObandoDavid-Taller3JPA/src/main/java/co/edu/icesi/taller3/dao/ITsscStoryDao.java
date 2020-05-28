package co.edu.icesi.taller3.dao;

import java.util.List;

import co.edu.icesi.taller3.model.TsscStory;

public interface ITsscStoryDao {
	
	
	
	public void save(TsscStory story);
	public void update(TsscStory story);
	public void delete(TsscStory story);
	public void deleteAll();
	public TsscStory findById(long id);
	public List<TsscStory> findAll();
}
