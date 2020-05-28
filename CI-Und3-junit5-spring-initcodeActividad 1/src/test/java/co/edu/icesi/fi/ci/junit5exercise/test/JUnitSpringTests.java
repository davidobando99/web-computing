package co.edu.icesi.fi.ci.junit5exercise.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.fi.ci.junit5exercise.main.AppConfig;
import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class JUnitSpringTests {

	private OrderService MyOrder;

	@Autowired
	public JUnitSpringTests(OrderService myOrder) {
		MyOrder= myOrder;
	}
	@BeforeAll
	public static void setUp() {
		
		System.out.println("-----> SETUP <-----");
		
	}

	@Test
	public void testSampleServiceGetAccountDescription() {
		// Check if the return description has a 'Description:' string.
		
		assertTrue(MyOrder.getOrderDescription(01).startsWith("Description:"));
		
	}

	@Test
	public void testSampleServiceGetAccountCode() {
		// Check if the return description has a 'Code:' string.
		assertTrue(MyOrder.getOrderStringCode(01).startsWith("Account Code:"));
	}
	
	@Test
	public void testSampleServiceCreateOrder() {
		// Check if the return description has a 'Code:' string.
		Order order =MyOrder.createOrder(new Order());
		
		order.setSecurityCode("A00348505");
		order.setDescription("Douglas es bimba");
		
		assertNotNull(order.getDescription());
		assertNotNull(order.getSecurityCode());
		//assertEquals(order.getDescription(), "A00348505");
		//assertEquals(order.getSecurityCode(), "Douglas es bimba");
	}
	
	@Test
	public void testSampleServiceGetOrder() {
		// Check if the return description has a 'Code:' string.
		Order order =MyOrder.getOrder(01);
		
		
		
		assertNotNull(order.getDescription());
		assertNotNull(order.getSecurityCode());
		assertNotNull(order.getOrderDate());
		assertNotNull(order.getOrderStatus());
		assertNotNull(order.getOrderId());
		
	}
	
	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
