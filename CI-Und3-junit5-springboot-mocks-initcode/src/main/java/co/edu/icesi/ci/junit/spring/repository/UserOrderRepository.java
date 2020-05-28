package co.edu.icesi.ci.junit.spring.repository;

import org.hibernate.criterion.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.junit.spring.model.UserOrder;
@Repository
public interface UserOrderRepository extends CrudRepository<UserOrder, Integer>{
	
	

}
