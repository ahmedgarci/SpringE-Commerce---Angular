package com.example.SpringE_Commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringECommerceApplication.class, args);
	}

}
