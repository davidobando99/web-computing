package co.edu.icesi.taller3.dao;

import co.edu.icesi.taller3.model.TsscTimecontrol;

public interface ITsscTimecontrolDao {
	
	
	
	public void save(TsscTimecontrol time);


	public void update(TsscTimecontrol time);
	
	public void delete(TsscTimecontrol time);
	public void deleteAll();
}
