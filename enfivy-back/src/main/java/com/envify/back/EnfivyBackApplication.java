package com.envify.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories
public class EnfivyBackApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EnfivyBackApplication.class, args);
	}

}
