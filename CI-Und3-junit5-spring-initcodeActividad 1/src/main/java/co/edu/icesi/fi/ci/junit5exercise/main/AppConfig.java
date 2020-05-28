package co.edu.icesi.fi.ci.junit5exercise.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderServiceImpl;

@Configuration
public class AppConfig {
	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl();
	}
}
