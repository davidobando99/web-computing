package co.edu.icesi.ci.thymeval;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.model.UserType;
import co.edu.icesi.ci.thymeval.repository.UserRepository;
import co.edu.icesi.ci.thymeval.service.UserService;

@SpringBootApplication
public class ThymeleafValidationApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		
		SpringApplication.run(ThymeleafValidationApplication.class, args);
     	
		
	}
	
	@Bean
	public CommandLineRunner command(UserRepository userRepo) {
		return(args)->{
			UserApp user1 = new UserApp();
			user1.setName("Juan");
			user1.setEmail("jc@gmail.com");
			user1.setUsername("user1");
			user1.setType(UserType.admin);
			user1.setPassword("{noop}123");
			userRepo.save(user1);
			
			UserApp user2 = new UserApp();
			user2.setUsername("user2");
			user2.setName("Ana");
			user2.setEmail("ana@gmail.com");
			user2.setType(UserType.doctor);
			user2.setPassword("{noop}123");
			userRepo.save(user2);
			
			UserApp user3 = new UserApp();
			user3.setUsername("user3");
			user3.setName("Juan");
			user3.setEmail("juan@gmail.com");
			user3.setType(UserType.patient);
			user3.setPassword("{noop}123");
			userRepo.save(user3);
		};
	}

	
	
}
