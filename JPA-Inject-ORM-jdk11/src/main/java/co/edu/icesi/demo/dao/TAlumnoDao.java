package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.model.TAlumno;

@Repository
@Scope("singleton")
public class TAlumnoDao implements ITAlumnoDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TAlumno entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TAlumno entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TAlumno entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TAlumno findById(Integer codigo) {
		return entityManager.find(TAlumno.class, codigo);		
	}

	@Override
	public List<TAlumno> findAll() {
		String jpql = "Select a from TAlumno a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
