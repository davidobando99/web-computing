package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface ITsscStoryDAO {
	
	public void save(TsscStory story);
	public void update(TsscStory story);
	public void delete(TsscStory story);
	public void deleteAll();
	public List<TsscStory> findById(long id);
	public List<TsscStory> findAll();
	
}
