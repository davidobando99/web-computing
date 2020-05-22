package co.edu.icesi.fi.tics.tssc;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.AdminService;
import co.edu.icesi.fi.tics.tssc.services.TopicService;

@SpringBootApplication
//@EnableWebMvc
public class TallerPersistenciaApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(TallerPersistenciaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AdminService adminService, TopicService topicService) {
		return (args) -> {

			// Agregar un usuario superadmin.
			TsscAdmin user = new TsscAdmin();
			user.setUser("superadmin");
			user.setPassword("{noop}123");
			user.setSuperAdmin("superadmin");
			adminService.save(user);

			// Agregar un usuario admin.
			TsscAdmin user2 = new TsscAdmin();
			user2.setUser("admin");
			user2.setPassword("{noop}123");
			user2.setSuperAdmin("admin");
			adminService.save(user2);
			
		};

	}

}
