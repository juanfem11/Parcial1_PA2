package com.parcial1.parcial1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableReactiveMongoRepositories("com.parcial1.parcial1.repository") // Asegura que detecte el repositorio
public class Parcial1Application {

	@PostConstruct
	public void init() {
		System.out.println("✅ Parcial1Application iniciada correctamente");
	}

	public static void main(String[] args) {
		SpringApplication.run(Parcial1Application.class, args);
	}
}
