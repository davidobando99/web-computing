package co.edu.icesi.ci.junit.spring.service;

import java.util.Optional;

import co.edu.icesi.ci.junit.spring.exceptions.UserOrderNullException;
import co.edu.icesi.ci.junit.spring.model.UserOrder;

public interface UserOderService {



	public UserOrder getOrder(int id) throws UserOrderNullException;

	public UserOrder createOrder(UserOrder order) throws UserOrderNullException;

	public UserOrder updateOrder(UserOrder order);

	public void deleteOrder(UserOrder order);

}
