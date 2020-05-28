package co.edu.icesi.ci.junit.spring.service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.junit.spring.exceptions.UserOrderNullException;
import co.edu.icesi.ci.junit.spring.model.UserOrder;
import co.edu.icesi.ci.junit.spring.repository.UserOrderRepository;


@Service
public class UserOderServiceImpl implements UserOderService {
	
	private UserOrderRepository userRepository;

	@Autowired
	public UserOderServiceImpl (UserOrderRepository userRepository)
	{
		this.userRepository =  userRepository;
	}


	public UserOrder createOrder(UserOrder order) throws UserOrderNullException {
		
		UserOrder newOrder = userRepository.save(order);
		if(newOrder==null) {
			throw new UserOrderNullException();
		}
		return newOrder;
	}
	
	public UserOrder getOrder(int id) throws UserOrderNullException {
		Optional<UserOrder> existingOrder = userRepository.findById(id);
		if(existingOrder==null) {
			throw new UserOrderNullException();
		}
		return existingOrder.get();
	}
	
	public UserOrder updateOrder(UserOrder order) {
		return null;
	}
	public void deleteOrder(UserOrder order) {
	return;
	}


	

}
