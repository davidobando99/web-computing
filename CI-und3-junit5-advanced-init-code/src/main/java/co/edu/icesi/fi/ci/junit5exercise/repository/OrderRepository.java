package co.edu.icesi.fi.ci.junit5exercise.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
