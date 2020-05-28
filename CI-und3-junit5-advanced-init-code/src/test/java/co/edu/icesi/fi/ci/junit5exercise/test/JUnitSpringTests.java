package co.edu.icesi.fi.ci.junit5exercise.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = AppConfig.class)
public class JUnitSpringTests {

	private OrderService orderService;

	@Autowired
	public JUnitSpringTests(OrderService orderService) {
		this.orderService = orderService;
	}

	@BeforeAll
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}

	
	@Nested
	class CreateClass{
		@Test	
		@BeforeEach
		@DisplayName("Crear una nueva orden")
		@Tag("no_mutable")
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
		@Tag("mutable")
		@Test
		public void testUpdateOrder() {
			
		}
		@Tag("mutable")
		@Test
		public void testDeleteOrder() {
			
		}
		
		@Nested
		class ConsultClass{
			@Test
			@DisplayName("Consultar la descripcion de una orden")
			public void testSampleServiceGetAccountDescription() {
				// Check if the return description has a 'Description:' string.
				assertTrue(orderService.getOrderDescription(1).contains("Description"));
			}

			@Test
			@DisplayName("COnsultar el codigo de una orden")
			public void testSampleServiceGetAccountCode() {
				// Check if the return description has a 'Code:' string.
				assertTrue(orderService.getOrderStringCode(1).contains("XYZ"));
			}

			@Test
			@DisplayName("Consultar una orden")
			@Tag("no_mutable")
			public void testSampleServiceGetOrder() {

				Order existingOrder = orderService.getOrder(1);

				if (existingOrder != null) {
					assertTrue(orderService.getOrder(1) instanceof Order);
					assertNotNull("Security isn't null", existingOrder.getSecurityCode());
					assertNotNull("Description isn't null", existingOrder.getDescription());
				}
				assertNotNull(existingOrder, "Object is not null");
			}

			
		}
		
	}
	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}

	
}
