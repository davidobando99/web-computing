package co.edu.icesi.fi.ci.junit5exercise.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

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
import co.edu.icesi.fi.ci.junit5exercise.service.OrderServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class JUnitSpringTests {

	private OrderService MyOrder;
	private static Order order1;
	private static Order order2;

	@Autowired
	public JUnitSpringTests(OrderService myOrder) {
		MyOrder= myOrder;
	}
	@BeforeAll
	public static void setUp() {
		
		 order1 = new Order();
		 order1.setOrderId(1);
		 
		 order1.setOrderStatus(OrderServiceImpl.CANCELED);
		 order1.setSecurityCode("1245");
		 order1.setDescription("Douglas es bimba");
		 order1.setOrderDate(new Date());
		 
		 order2 = new Order();
		 order2.setOrderId(2);
		 order2.setOrderStatus(OrderServiceImpl.INITIATED);
		 order2.setSecurityCode("2486");
		 order2.setDescription("Douglas es bimba x2");
		 order2.setOrderDate(new Date());
		
	}

	@Test
	public void testSampleServiceGetAccountDescription() {
		// Check if the return description has a 'Description:' string.
		
		assertTrue(MyOrder.getOrderDescription(1).startsWith("Description:"));
		
	}

	@Test
	public void testSampleServiceGetAccountCode() {
		// Check if the return description has a 'Code:' string.
		assertTrue(MyOrder.getOrderStringCode(2).startsWith("Account Code:"));
	}
	
	@Test
	public void testSampleServiceCreateOrder() {
		// Check if the return description has a 'Code:' string.
		MyOrder.createOrder(order1);
		Order order = MyOrder.getOrder(1);
		
		assertNotNull(order);
		assertNotNull(order.getDescription());
		assertNotNull(order.getSecurityCode());
		assertNotNull(order.getOrderId());
		assertNotNull(order.getOrderStatus());
		assertNotNull(order.getOrderDate());
		
		//assertEquals(order.getDescription(), "A00348505");
		//assertEquals(order.getSecurityCode(), "Douglas es bimba");
	}
	
	@Test
	public void testSampleServiceGetOrder() {
		// Check if the return description has a 'Code:' string.
		MyOrder.createOrder(order1);
		MyOrder.createOrder(order2);
		Order order =MyOrder.getOrder(1);
		
		
		assertNotNull(order);
		assertNotNull(order.getDescription());
		assertNotNull(order.getSecurityCode());
		assertNotNull(order.getOrderDate());
		assertNotNull(order.getOrderStatus());
		assertNotNull(order.getOrderId());
		
	}
	
	@Test
	public void testSampleServiceDeleteOrder() {
		// Check if the return description has a 'Code:' string.
		MyOrder.createOrder(order1);
		MyOrder.createOrder(order2);
		
		MyOrder.deleteOrder(1);
		
		
		assertNull(MyOrder.getOrder(1));
		
		
	}
	
	@Test
	public void testSampleServiceDeleteOrders() {
		// Check if the return description has a 'Code:' string.
		MyOrder.createOrder(order1);
		MyOrder.createOrder(order2);
		
		MyOrder.deleteOrders();
		
		
		assertNull(MyOrder.getOrder(1));
		assertNull(MyOrder.getOrder(2));
		
	}
	
	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
		
	}
}
