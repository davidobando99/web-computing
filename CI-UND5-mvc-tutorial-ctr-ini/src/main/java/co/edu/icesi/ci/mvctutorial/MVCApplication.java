package co.edu.icesi.ci.mvctutorial;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.model.UserType;
import co.edu.icesi.ci.mvctutorial.service.UserService;

@SpringBootApplication
public class MVCApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(MVCApplication.class, args);
		UserService u = c.getBean(UserService.class);
		User user1 = new User();
		user1.setName("Juan");
		user1.setEmail("jc@gmail.com");
		user1.setType(UserType.doctor);
		u.save(user1);
		User user2 = new User();
		user2.setName("Ana");
		user2.setEmail("ana@gmail.com");
		user2.setType(UserType.patient);
		u.save(user2);
	}

}
