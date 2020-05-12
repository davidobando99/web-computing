package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
@Scope("singleton")
public class TsscTopicDAO implements ITsscTopicDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscTopic topic) {
		entityManager.persist(topic);
		
	}

	@Override
	public void update(TsscTopic topic) {
		entityManager.merge(topic);
		
	}

	@Override
	public void delete(TsscTopic topic) {
		entityManager.remove(topic);
		
	}

	@Override
	public List<TsscTopic> findByName(String name) {
		String cons = "Select a from TsscTopic a WHERE a.name = '"+name+"'";
		return entityManager.createQuery(cons).getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		String cons = "Select a from TsscTopic a WHERE a.description = :description";
		  TypedQuery<TsscTopic> q = entityManager.createQuery(cons, TsscTopic.class).setParameter("description", description);
		return q.getResultList();
	}

	@Override
	public List<TsscTopic> findById(long id) {
		String cons = "Select a from TsscTopic a WHERE a.id = '"+id+"'";
		TypedQuery<TsscTopic> q = entityManager.createQuery(cons, TsscTopic.class);
		return q.getResultList();
	}

	@Override
	public List<TsscTopic> findAll() {
		String cons = "Select a from TsscTopic a";
		TypedQuery<TsscTopic> q = entityManager.createQuery(cons, TsscTopic.class);
		return q.getResultList();
	}

	@Override
	public void deleteAll() {
		String jpql = "Delete From TsscTopic";
		entityManager.createQuery(jpql).executeUpdate();
		
	}
}
