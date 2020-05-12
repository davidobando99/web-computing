package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.context.spi.CurrentSessionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Repository
@Scope("singleton")
public class TsscStoryDAO implements ITsscStoryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscStory story) {
		entityManager.persist(story);
		
	}

	@Override
	public void update(TsscStory story) {
		entityManager.merge(story);
		
	}

	@Override
	public void delete(TsscStory story) {
		entityManager.remove(story);
		
	}

	@Override
	public List<TsscStory> findById(long id) {
		String cons = "Select a from TsscStory a WHERE a.id = :f";
		TypedQuery<TsscStory> q = entityManager.createQuery(cons, TsscStory.class)
		.setParameter("f", id);
		return q.getResultList();
	}

	@Override
	public List<TsscStory> findAll() {
		String cons = "Select a from TsscStory a";
		TypedQuery<TsscStory> q = entityManager.createQuery(cons, TsscStory.class);
		return q.getResultList();
	}

	@Override
	public void deleteAll() {
		String jpql = "Delete From TsscStory";
		entityManager.createQuery(jpql).executeUpdate();
	}
}
