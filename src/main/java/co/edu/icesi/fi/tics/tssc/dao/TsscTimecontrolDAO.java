package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TsscTimecontrolDAO implements ITsscTimecontrolDAO{

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
	public List<TsscTimecontrol> findById(long id) {
		String cons = "Select a from TsscTimecontrol a WHERE a.id = :d";
		TypedQuery<TsscTimecontrol> q = entityManager.createQuery(cons, TsscTimecontrol.class);
		q.setParameter("d", id);
		return q.getResultList();
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String cons = "Select a from TsscTimecontrol a";
		TypedQuery<TsscTimecontrol> q = entityManager.createQuery(cons, TsscTimecontrol.class);
		return q.getResultList();
	}

	@Override
	public void deleteAll() {
		String jpql = "Delete From TsscTimecontrol";
		entityManager.createQuery(jpql).executeUpdate();		
	}
}
