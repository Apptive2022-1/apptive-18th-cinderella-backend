package com.example.cinderella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CinderellaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinderellaApplication.class, args);
	}

}
