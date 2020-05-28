package co.edu.icesi.taller2;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.taller2.model.TsscAdmin;
import co.edu.icesi.taller2.model.TsscGame;
import co.edu.icesi.taller2.model.TsscTopic;
import co.edu.icesi.taller2.repository.AdminRepository;
import co.edu.icesi.taller2.repository.GameRepository;
import co.edu.icesi.taller2.repository.TopicRepository;

@SpringBootApplication
public class Taller2ThymeleafApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	public static void main(String[] args) {
		SpringApplication.run(Taller2ThymeleafApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner command(AdminRepository adminRepo, TopicRepository topicRepo
		) {
		return(args)->{
			TsscAdmin user1 = new TsscAdmin();
			user1.setUser("user1");
			user1.setPassword("{noop}123");
			user1.setSuperAdmin("admin");
			adminRepo.save(user1);
			
			TsscAdmin user2 = new TsscAdmin();
			user2.setUser("user2");
			user2.setPassword("{noop}123");
			user2.setSuperAdmin("superAdmin");
			adminRepo.save(user2);
			
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			topic.setName("Tema 1");
			topic.setGroupPrefix("f");
			topic.setDescription("efe");
			topicRepo.save(topic);
			
			TsscTopic topic2 = new TsscTopic();
			topic2.setDefaultGroups(1);
			topic2.setDefaultSprints(1);
			topic2.setName("Tema 2");
			topic2.setGroupPrefix("f");
			topic2.setDescription("efe");
			topicRepo.save(topic2);
			
		
			
			
		};
	}
	

}
