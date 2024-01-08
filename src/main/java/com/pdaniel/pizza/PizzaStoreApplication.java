package com.pdaniel.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pdaniel.pizza.persistence.repository")
@EnableJpaAuditing
public class PizzaStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaStoreApplication.class, args);
	}

}
