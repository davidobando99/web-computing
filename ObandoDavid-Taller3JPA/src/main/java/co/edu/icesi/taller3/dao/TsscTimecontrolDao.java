package co.edu.icesi.taller3.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.model.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TsscTimecontrolDao implements ITsscTimecontrolDao{
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public void save(TsscTimecontrol time) {
		entityManager.persist(time);
	}

	@Override
	public void update(TsscTimecontrol time) {
		entityManager.merge(time);
	}

	@Override
	public void delete(TsscTimecontrol time) {
		entityManager.remove(time);
	}
	
	@Override
	public void deleteAll() {
		String jpql = "Delete from TsscTimecontrol";
		 entityManager.createQuery(jpql).executeUpdate();
	}
	

}
