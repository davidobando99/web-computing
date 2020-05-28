package co.edu.icesi.taller3.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.taller3.model.TsscTopic;

@Repository
@Scope("singleton")
public class TsscTopicDao implements ITsscTopicDao{
	
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
	public void deleteAll() {
		String jpql = "Delete from TsscTopic";
		 entityManager.createQuery(jpql).executeUpdate();
	}
	
	
	@Override
	public TsscTopic findById(long id) {
		return entityManager.find(TsscTopic.class, id);
	}
	
	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select t from TsscTopic t";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<TsscTopic> findByName(String name) {
		String jpql = "Select t from TsscTopic t WHERE t.name = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		String jpql = "Select t from TsscTopic t WHERE t.description = '" + description + "'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Object[]> getTopicByGameDate(LocalDate date) {
		String jpql = "Select t,count(g) from TsscTopic t INNER JOIN TsscGame g ON t.id = g.id "
				+ "GROUP BY t.id ORDER BY g.scheduledTime ASC";
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class).setParameter("date", date);
		return query.getResultList();
	}
	
	

}
