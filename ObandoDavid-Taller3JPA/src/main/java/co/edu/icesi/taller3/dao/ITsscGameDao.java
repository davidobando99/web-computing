package co.edu.icesi.taller3.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscTopic;

public interface ITsscGameDao {
	
	public void save(TsscGame game);
	public void update(TsscGame game);
	public void delete(TsscGame game);
	public TsscGame findById(long id);
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByDescription(String description);
	public void deleteAll();
	public List<TsscGame> findAll();
	public List<TsscGame> findByTopicId(long id);
	public List<TsscGame> findByDateRange(LocalDate initialDate, LocalDate finalDate);
	public List<TsscGame> findByDateHourRange(LocalDate date, LocalTime initialTime,LocalTime finalTime);
	public List<Object[]> getTopicByGameDate(LocalDate date);
	public List<TsscGame> findGameLess10StoriesOrNoTimeControl(LocalDate date);

}
