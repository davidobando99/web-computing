package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

public interface ITsscTimecontrolDAO {

	public void save(TsscTimecontrol time);
	public void update(TsscTimecontrol time);
	public void delete(TsscTimecontrol time);
	public void deleteAll();
	public List<TsscTimecontrol> findById(long id);
	public List<TsscTimecontrol> findAll();
}
