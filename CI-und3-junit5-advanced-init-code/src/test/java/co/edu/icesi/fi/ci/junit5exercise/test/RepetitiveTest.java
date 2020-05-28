package co.edu.icesi.fi.ci.junit5exercise.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RepetitiveTest {
	
	private OrderService orderService;

	@Autowired
	public RepetitiveTest(OrderService orderService) {
		this.orderService = orderService;
	}

	@BeforeAll
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	
	
	
	@DisplayName("Crear una nueva orden")
	@RepeatedTest(10)
	public void testSampleServiceCreateNewOrder() {
		Order newOrder = new Order();
		newOrder.setSecurityCode("XYZ");
		newOrder.setDescription("Description");
		newOrder.setOrderId(1);
		if (newOrder != null) {
			assertTrue(orderService.createOrder(newOrder) instanceof Order);
			assertNotNull("Security isn't null", newOrder.getSecurityCode());
			assertNotNull("Description isn't not null", newOrder.getDescription());
		}
		assertNotNull(newOrder, "New Order is not null");

	}


}
