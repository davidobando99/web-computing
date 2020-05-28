package co.edu.icesi.taller3.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class TsscAdminDao implements ITsscAdminDao{
	
	@PersistenceContext
	private EntityManager entityManager;

}
