package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
@Scope("singleton")
public class TsscAdminDAO implements ITsscAdminDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscAdmin admin) {
		entityManager.persist(admin);
		
	}

	@Override
	public void update(TsscAdmin admin) {
		entityManager.merge(admin);
		
	}

	@Override
	public void delete(TsscAdmin admin) {
		entityManager.remove(admin);
		
	}

	@Override
	public List<TsscAdmin> findById(long id) {
		String cons = "Select a from TsscAdmin a WHERE a.id = :d";
		TypedQuery<TsscAdmin> q = entityManager.createQuery(cons, TsscAdmin.class).setParameter("d", id);
		return q.getResultList();
	}

	@Override
	public List<TsscAdmin> findAll() {
		String cons = "Select a from TsscAdmin a";
		TypedQuery<TsscAdmin> q = entityManager.createQuery(cons, TsscAdmin.class);
		return q.getResultList();
	}
	
	
	
}
