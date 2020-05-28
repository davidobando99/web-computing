package co.edu.icesi.taller3.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscTopic;

@Repository
@Scope("singleton")
public class TsscGameDao implements ITsscGameDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscGame game) {
		entityManager.persist(game);
	}

	@Override
	public void update(TsscGame game) {
		entityManager.merge(game);
	}

	@Override
	public void delete(TsscGame game) {
		entityManager.remove(game);
	}
	@Override
	public void deleteAll() {
		String jpql = "Delete from TsscGame";
		 entityManager.createQuery(jpql).executeUpdate();
	}
	
	
	@Override
	public TsscGame findById(long id) {
		return entityManager.find(TsscGame.class, id);
	}
	
	@Override
	public List<TsscGame> findAll() {
		String jpql = "Select t from TsscGame t";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<TsscGame> findByName(String name) {
		String jpql = "Select t from TsscGame t WHERE t.name = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDescription(String description) {
		String jpql = "Select t from TsscGame t WHERE t.description = '" + description + "'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<TsscGame> findByTopicId(long id) {
		String jpql = "Select t from TsscGame t WHERE t.tsscTopic.id =:d";
		TypedQuery<TsscGame> query = entityManager.createQuery(jpql, TsscGame.class).setParameter("d", id);
		return query.getResultList();
	}
	
	@Override
	public List<TsscGame> findByDateRange(LocalDate initialDate, LocalDate finalDate) {
		String jpql = "Select t from TsscGame t WHERE t.scheduledDate BETWEEN :initial AND :final";
		
		TypedQuery<TsscGame> query = entityManager.createQuery(jpql, TsscGame.class).setParameter("initial", initialDate).setParameter("final", finalDate);
		return query.getResultList();
	}
	
	@Override
	public List<TsscGame> findByDateHourRange(LocalDate date, LocalTime initialTime,LocalTime finalTime ) {
		String jpql = "Select t from TsscGame t WHERE t.scheduledDate =:date AND (t.scheduledTime BETWEEN :initial AND :final)";
		
		TypedQuery<TsscGame> query = entityManager.createQuery(jpql, TsscGame.class).setParameter("date", date).
				setParameter("initial", initialTime).setParameter("final", finalTime);
		return query.getResultList();
	}
	
	@Override
	public List<Object[]> getTopicByGameDate(LocalDate scheduleDate) {
//		String jpql1 = "Select t.tsscTopic, count(t),t.id from TsscGame t WHERE "
//				+ ":date =t.scheduledDate GROUP BY t.tsscTopic ORDER BY t.scheduledTime ASC";
//		
		
		String jpql = "Select t.tsscTopic, count(t) from TsscGame t where :date = t.scheduledDate"
				+ " group by t.tsscTopic";
		
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class).setParameter("date", scheduleDate);
		return query.getResultList();
	}
	
	public List<TsscGame> findGameLess10StoriesOrNoTimeControl(LocalDate date){
		
		String jpql = "Select t from TsscGame t WHERE (t.scheduledDate =:date AND ((("
				+ "(SELECT Count(s) FROM TsscStory s WHERE s.tsscGame.id = t.id ) < 10)) OR "+
				"(SELECT Count(c) FROM TsscTimecontrol c WHERE c.tsscGame.id = t.id) <1))";
		
		
		
		TypedQuery<TsscGame> query = entityManager.createQuery(jpql, TsscGame.class).setParameter("date", date);
		
		return query.getResultList();
	}

}
