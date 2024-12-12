package com.gayathma.oop.coursework.backend.OOP_CW_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.gayathma.oop.coursework.backend.OOP_CW_BE.repository")
@EntityScan(basePackages = "com.gayathma.oop.coursework.backend.OOP_CW_BE.model")
public class OopCwBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(OopCwBeApplication.class, args);
	}
}


