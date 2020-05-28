package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ExampleBeansLyfecycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleBeansLyfecycleApplication.class, args);
		ConfigurableApplicationContext applicationContext = new 
		AnnotationConfigApplicationContext(MyBeanPostProcessor.class);
		applicationContext.close();
	}

}
