package co.edu.icesi.fi.ci.junit5exercise.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;
@Component
public class OrderServiceImpl implements OrderService {

	
	private HashMap<Integer, Order> orders = new HashMap<Integer,Order>();
	public static final String INITIATED ="INITIATED";
	public static final String SHIPPED ="SHIPPED";
	public static final String CANCELED ="CANCELED";
	public static final String IN_WAREHOUSE ="IN WAREHOUSE";
	
	
	//dummy method
	public String getOrderDescription(int id) {
		Order existingOrder = orders.get(id);
		existingOrder.setDescription("Order for XYZ in units");
		return "Description: " + existingOrder.getDescription();
	}

	//dummy method
	public String getOrderStringCode(int id) {
		Order existingOrder = orders.get(id);
		existingOrder.setSecurityCode("XYZ");
		return "Account Code: " + existingOrder.getSecurityCode();
	}
	
	//dummy method
	public Order createOrder(Order order) {
	
		if(order.getDescription()!=null && order.getOrderDate()!=null && 
				order.getOrderStatus()!=null && order.getSecurityCode()!=null && 
				(order.getOrderStatus().equals(INITIATED)||order.getOrderStatus().equals(SHIPPED)
						||order.getOrderStatus().equals(CANCELED)||order.getOrderStatus().equals(IN_WAREHOUSE))
				&&order.getSecurityCode().length()==4) {
			orders.put(order.getOrderId(), order);
		}
			
		
		return order;
	}
	
	
	public HashMap<Integer, Order> getOrders() {
		return orders;
	}
	
	
	//dummy method
	public Order getOrder(int id) {
		orders.get(id);
		Order newOrder = orders.get(id);
		
		
		return newOrder;
	}
	
	public void deleteOrders() {
		orders.clear();
	}
	
	public Order deleteOrder(int id) {
		Order order = orders.get(id);
		orders.remove(id, order);
		return order;
	}
	
	

}
