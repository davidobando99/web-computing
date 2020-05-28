package co.edu.icesi.fi.ci.junit5exercise.service;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;

public interface OrderService {

	public String getOrderDescription(int id);
	public String getOrderStringCode(int id);
	public Order getOrder(int id);
	public Order createOrder(Order order);
	
	
}
