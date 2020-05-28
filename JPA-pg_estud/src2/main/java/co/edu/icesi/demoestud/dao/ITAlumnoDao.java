package co.edu.icesi.demoestud.dao;


import java.util.List;

import co.edu.icesi.demoestud.model.TAlumno;

public interface ITAlumnoDao {

	public void save(TAlumno entity);
	public void update(TAlumno entity);
	public void delete(TAlumno entity);
	public TAlumno findById(Integer codigo);
	public List<TAlumno> findAll();
	
}
