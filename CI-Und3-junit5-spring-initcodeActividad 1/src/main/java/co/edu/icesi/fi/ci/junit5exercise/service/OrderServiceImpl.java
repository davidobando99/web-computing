package co.edu.icesi.fi.ci.junit5exercise.service;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;
@Component
public class OrderServiceImpl implements OrderService {

	//dummy method
	public String getOrderDescription(int id) {
		Order existingOrder = new Order();
		existingOrder.setDescription("Order for XYZ in units");
		return "Description: " + existingOrder.getDescription();
	}

	//dummy method
	public String getOrderStringCode(int id) {
		Order existingOrder = new Order();
		existingOrder.setSecurityCode("XYZ");
		return "Account Code: " + existingOrder.getSecurityCode();
	}
	
	//dummy method
	public Order createOrder(Order order) {
		Order newOrder = new Order();
		newOrder.setOrderId(new Random().nextInt());
		newOrder.setSecurityCode("XYZ");
		newOrder.setOrderStatus("INITIATED");
		newOrder.setOrderDate(new Date());
		return newOrder;
	}
	
	//dummy method
	public Order getOrder(int id) {
		Order newOrder = new Order();
		newOrder.setOrderId(new Random().nextInt());
		newOrder.setSecurityCode("XYZ");
		newOrder.setOrderStatus("COMPLETED");
		newOrder.setOrderDate(new Date());
		newOrder.setDescription("This is the new XYZ order");
		return newOrder;
	}
	
	

}
