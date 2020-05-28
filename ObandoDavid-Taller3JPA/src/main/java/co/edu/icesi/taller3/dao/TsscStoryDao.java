package co.edu.icesi.taller3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscStory;

@Repository
@Scope("singleton")
public class TsscStoryDao implements ITsscStoryDao{
	
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
	public void deleteAll() {
		String jpql = "Delete from TsscStory";
		 entityManager.createQuery(jpql).executeUpdate();
	}
	
	@Override
	public TsscStory findById(long id) {
		return entityManager.find(TsscStory.class, id);
	}
	
	@Override
	public List<TsscStory> findAll() {
		String jpql = "Select t from TsscStory t";
		return entityManager.createQuery(jpql).getResultList();
	}

}
