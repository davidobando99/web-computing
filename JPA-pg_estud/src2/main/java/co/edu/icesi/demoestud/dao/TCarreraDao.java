package co.edu.icesi.demoestud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demoestud.model.TAlumno;
import co.edu.icesi.demoestud.model.TCarrera;

@Repository
@Scope("singleton")
public class TCarreraDao implements ITCarreraDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public TCarrera findById(Integer codigo) {
		return entityManager.find(TCarrera.class, codigo);		
	}
	@Override
	public TCarrera maxCareer() {
			String jpql1 ="Select COUNT(tcarrera0_) from t_carreras tcarrera0_"
					+ " inner join t_programas tprogramas1_ where tcarrera0_.codigo=tprogramas1_.carrera";
		String jpql = "Select MAX(COUNT(a.TProgramas)) from TCarrera a";
		return 	(TCarrera)entityManager.createQuery(jpql1).getSingleResult();	
	}

}
