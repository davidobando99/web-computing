package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface ITsscAdminDAO {

	public void save(TsscAdmin admin);
	public void update(TsscAdmin admin);
	public void delete(TsscAdmin admin);
	public List<TsscAdmin> findById(long id);
	public List<TsscAdmin> findAll();
}
