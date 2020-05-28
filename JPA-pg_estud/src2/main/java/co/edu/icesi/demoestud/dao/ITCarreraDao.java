package co.edu.icesi.demoestud.dao;

import java.util.List;

import co.edu.icesi.demoestud.model.TCarrera;

public interface ITCarreraDao {
	
	public TCarrera maxCareer();
	public TCarrera findById(Integer codigo);
}
