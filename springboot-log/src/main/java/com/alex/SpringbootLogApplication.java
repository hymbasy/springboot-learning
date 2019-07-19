package com.alex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLogApplication.class, args);
	}

}
