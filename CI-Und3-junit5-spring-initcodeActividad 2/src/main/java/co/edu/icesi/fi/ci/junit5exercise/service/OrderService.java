package co.edu.icesi.fi.ci.junit5exercise.service;

import java.util.HashMap;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;

public interface OrderService {

	public String getOrderDescription(int id);
	public String getOrderStringCode(int id);
	public Order getOrder(int id);
	public Order createOrder(Order order);
	public void deleteOrders();
	public Order deleteOrder(int id);
	public HashMap<Integer, Order> getOrders();
	
	
}
