package co.edu.icesi.ci.junit.spring;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.ci.junit.spring.exceptions.UserOrderNullException;
import co.edu.icesi.ci.junit.spring.model.UserOrder;
import co.edu.icesi.ci.junit.spring.service.UserOderService;

import lombok.extern.java.Log;

@Log
@ExtendWith(SpringExtension.class)
public class JUnitSpringExample {

	private static UserOrder userOrder;
	
	
	@Autowired
	private UserOderService userOderService;

	@BeforeAll
	public static void setUp() {
		log.info("-----> SETUP <-----");
		userOrder = new UserOrder();
				userOrder.setOrderId(1);
				 
				userOrder.setOrderStatus("");
				userOrder.setSecurityCode("1245");
				 userOrder.setDescription("Douglas es bimba");
				 userOrder.setOrderDate(new Date());
	}

	

	

	@Test
	public void testSampleServiceCreateOrder() {
		// Check if the return description has a 'Code:' string.
		try {
			userOderService.createOrder(userOrder);
		} catch (UserOrderNullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			UserOrder order = userOderService.getOrder(1);
			assertNotNull(order);
			assertNotNull(order.getDescription());
			assertNotNull(order.getSecurityCode());
			assertNotNull(order.getOrderId());
			assertNotNull(order.getOrderStatus());
			assertNotNull(order.getOrderDate());
		} catch (UserOrderNullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//assertEquals(order.getDescription(), "A00348505");
		//assertEquals(order.getSecurityCode(), "Douglas es bimba");
	}
	
	@Test
	public void testSampleServiceGetOrder() {
		// Check if the return description has a 'Code:' string.
		try {
			userOderService.createOrder(userOrder);
		} catch (UserOrderNullException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		UserOrder order;
		try {
			order = userOderService.getOrder(1);
			assertNotNull(order);
			assertNotNull(order.getDescription());
			assertNotNull(order.getSecurityCode());
			assertNotNull(order.getOrderDate());
			assertNotNull(order.getOrderStatus());
			assertNotNull(order.getOrderId());
		} catch (UserOrderNullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	@AfterAll
	public static void afterTest() {
		log.info("-----> DESTROY <-----");
	}
}
